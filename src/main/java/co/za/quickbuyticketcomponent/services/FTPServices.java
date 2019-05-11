package co.za.quickbuyticketcomponent.services;


import co.za.quickbuyticketcomponent.modals.CustomerTickets;
import co.za.quickbuyticketcomponent.utils.FTPConfigProperties;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Component
public class FTPServices {

    @Autowired
    FTPConfigProperties ftpConfigProperties;

    static Logger logger = LoggerFactory.getLogger(FTPServices.class);

    public void uploadQRCodeToServer(CustomerTickets customerTickets, File file) {

        FTPClient ftpClient = new FTPClient();
        try {
            try {
                ftpClient.connect(ftpConfigProperties.getServer(), ftpConfigProperties.getPort());
            } catch (Exception e) {
                ftpClient.connect(ftpConfigProperties.getServer(), ftpConfigProperties.getPort());
            }

            ftpClient.login(ftpConfigProperties.getUser(), ftpConfigProperties.getPass());
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            String user = String.valueOf(customerTickets.getUserId().getUserId()) + "/" + customerTickets.getReferenceNumber();
            boolean existed = ftpClient.changeWorkingDirectory(user);
            if (!existed) {
                boolean created = makeDirectories(ftpClient, user);
                if (created) {
                    logger.info("Created Directory {}", user);
                    ftpClient.changeWorkingDirectory(user);
                } else {
                    logger.info("Couldn't not create directory: " + user);
                }
            }
            String firstRemoteFile = file.getName();
            InputStream inputStream = new FileInputStream(file);

            logger.info("Start uploading first file");
            boolean done = ftpClient.storeFile(firstRemoteFile, inputStream);

            inputStream.close();
            if (done) {
                logger.info("The first file is uploaded successfully.");
            }
        } catch (IOException ex) {
            logger.info("Error: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static boolean makeDirectories(FTPClient ftpClient, String dirPath)
            throws IOException {
        ftpClient.changeWorkingDirectory("/public_html");
        String[] pathElements = dirPath.split("/");
        if (pathElements != null && pathElements.length > 0) {
            for (String singleDir : pathElements) {
                boolean existed = ftpClient.changeWorkingDirectory(singleDir);
                if (!existed) {
                    boolean created = ftpClient.makeDirectory(singleDir);
                    if (created) {
                        logger.info("CREATED directory {} " + singleDir);
                        ftpClient.changeWorkingDirectory(singleDir);
                    } else {
                        logger.info("COULD NOT create directory {}" + singleDir);
                        return false;
                    }
                }
            }
        }
        return true;
    }

}

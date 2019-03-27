package co.za.quickbuyticketcomponent.services;


import co.za.quickbuyticketcomponent.modals.CustomerTickets;
import co.za.quickbuyticketcomponent.modals.UserProfile;
import co.za.quickbuyticketcomponent.utils.FTPConfigProperties;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
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

    public void uploadQRCodeToServer(CustomerTickets customerTickets,File qrCode,File mailAttachment) {

        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.connect(ftpConfigProperties.getServer(), ftpConfigProperties.getPort());
            ftpClient.login(ftpConfigProperties.getUser(), ftpConfigProperties.getPass());
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            String user = String.valueOf(customerTickets.getUserId().getUserId())+"/"+customerTickets.getReferenceNumber();
            boolean existed = ftpClient.changeWorkingDirectory(user);
            if (!existed) {
                boolean created = makeDirectories(ftpClient,user);
                if (created) {
                    System.out.println("CREATED directory: " + user);
                    ftpClient.changeWorkingDirectory(user);
                } else {
                    System.out.println("COULD NOT create directory: " + user);
                }
            }
            String firstRemoteFile = qrCode.getName();
            InputStream inputStream = new FileInputStream(qrCode);

            System.out.println("Start uploading first file");
            boolean done = ftpClient.storeFile(firstRemoteFile, inputStream);
            boolean pdffile = ftpClient.storeFile(mailAttachment.getName(), new FileInputStream(mailAttachment));
            inputStream.close();
            if (done && pdffile) {
                System.out.println("The first file is uploaded successfully.");
            }
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
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
                        System.out.println("CREATED directory: " + singleDir);
                        ftpClient.changeWorkingDirectory(singleDir);
                    } else {
                        System.out.println("COULD NOT create directory: " + singleDir);
                        return false;
                    }
                }
            }
        }
        return true;
    }

}

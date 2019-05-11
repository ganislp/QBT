package co.za.quickbuyticketcomponent.services;

import co.za.quickbuyticketcomponent.modals.CustomerTickets;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Table;
import com.itextpdf.text.DocumentException;
import net.glxn.qrgen.core.image.ImageType;
import net.glxn.qrgen.javase.QRCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.HashMap;

@Component
public class QRCodeServices {

    Logger logger = LoggerFactory.getLogger(QRCodeServices.class);

    @Autowired
    FTPServices ftpServices;

    public HashMap<String, Object> createQrCodeServices(CustomerTickets customerTickets) throws IOException, DocumentException {

        File qrCodeFile = createQRCodeByReferenceNumber(customerTickets.getReferenceNumber());
        ftpServices.uploadQRCodeToServer(customerTickets, qrCodeFile);
        File mailAttachment = createEmailNotificationAttachment(customerTickets, qrCodeFile);
        ftpServices.uploadQRCodeToServer(customerTickets, mailAttachment);
        HashMap<String, Object> map = new HashMap<>();
        map.put("pdf", mailAttachment);
        map.put("qrcode", qrCodeFile);
        map.put("pdfname", mailAttachment.getName());
        map.put("qrcodename", qrCodeFile.getName());
        logger.info("Pdf :" + mailAttachment.getAbsolutePath());
        logger.info("png :" + qrCodeFile.getAbsolutePath());
        return map;
    }

    private File createQRCodeByReferenceNumber(String referenceNumber) throws IOException {

        ByteArrayOutputStream bout =
                QRCode.from(referenceNumber)
                        .withSize(500, 500)
                        .to(ImageType.PNG)
                        .stream();

        File qrCodeImage = File.createTempFile("Reference", ".png");
        OutputStream out = new FileOutputStream(qrCodeImage);
        bout.writeTo(out);
        out.flush();
        out.close();

        return qrCodeImage;
//        File oldName = new File(qrCodeImage.getName());
//        File newFileName = new File(referenceNumber+".png");
//        boolean b = oldName.renameTo(newFileName);
//
//        if (b) {
//            qrCodeImage.delete();
//            oldName.delete();
//            return newFileName;
//        } else {
//
//            throw new IllegalArgumentException("QR Code Creation failed");
//        }
    }

    private File createMailAttachment(CustomerTickets customerTickets, File qrCodeImage) throws IOException {
        File barCodePdf = File.createTempFile("barcode", ".pdf");
        PdfWriter writer = new PdfWriter(barCodePdf.getAbsolutePath());
        PdfDocument pdf = new PdfDocument(writer);
        com.itextpdf.layout.Document document = new com.itextpdf.layout.Document(pdf);
        ImageData data = ImageDataFactory.create(qrCodeImage.getAbsolutePath());
        com.itextpdf.layout.element.Image image = new com.itextpdf.layout.element.Image(data);
        document.add(image);
        document.close();

//        File oldName = new File(barCodePdf.getName());
//        File newFileName = new File(customerTickets.getReferenceNumber()+".pdf");
//        boolean b = oldName.renameTo(newFileName);
//
//        if (b) {
//            barCodePdf.delete();
//            oldName.delete();
//            return newFileName;
//        } else {
//            throw new IllegalArgumentException("QR Code Creation failed");
//        }
        return barCodePdf;
    }


    private File createEmailNotificationAttachment(CustomerTickets customerTickets, File qrCodeImage) {
        // Creating a PdfWriter object
        File barCodePdf = null;
        ImageData logoData = null;
        try {
            logoData = ImageDataFactory.create("http://technologykings.co.za/QUICK-BUY-FINAL.png");


            // Creating the image
            Image logoImg = new Image(logoData);
//
            logoImg.scaleAbsolute(200, 100);
            logoImg.setMarginLeft(190f);

            barCodePdf = File.createTempFile("barcode", ".pdf");

            PdfWriter writer = new PdfWriter(barCodePdf.getAbsolutePath());

            // Creating a PdfDocument object
            PdfDocument pdfDoc = new PdfDocument(writer);

            // Creating a Document object
            Document doc = new Document(pdfDoc);
            doc.add(new Table(3));
            doc.add(logoImg);
            // Creating a table
            float[] pointColumnWidths = {300f, 300f};
            Table table = new Table(pointColumnWidths);

            // Populating row 1 and adding it to the table
            Cell cell1 = new Cell();
            cell1.add("Name ");
            table.addCell(cell1);

            Cell cell2 = new Cell();
            cell2.add(customerTickets.getUserId().getFirstName() + " " + customerTickets.getUserId().getLastName());
            table.addCell(cell2);

            // Populating row 2 and adding it to the table
            Cell cell3 = new Cell();
            cell3.add("Reference Number");
            table.addCell(cell3);

            Cell cell4 = new Cell();
            cell4.add(customerTickets.getReferenceNumber());
            table.addCell(cell4);

            if(customerTickets.getComboTickets() != 0) {
                // Populating row 4 and adding it to the table
                Cell cell71 = new Cell();
                cell71.add("Number Of VIP Tickets");
                table.addCell(cell71);

                Cell cell81 = new Cell();
                cell81.add(String.valueOf(customerTickets.getComboTickets()));
                table.addCell(cell81);
            }

            if(customerTickets.getCricketTickets() != 0) {
                // Populating row 3 and adding it to the table
                Cell cell5 = new Cell();
                cell5.add("Number Of Cricket Tickets");
                table.addCell(cell5);


                Cell cell6 = new Cell();
                cell6.add(String.valueOf(customerTickets.getCricketTickets()));
                table.addCell(cell6);

            }

            if(customerTickets.getCulturalTickets() != 0) {
                // Populating row 4 and adding it to the table
                Cell cell7 = new Cell();
                cell7.add("Number Of Cultural Tickets");
                table.addCell(cell7);

                Cell cell8 = new Cell();
                cell8.add(String.valueOf(customerTickets.getCulturalTickets()));
                table.addCell(cell8);
            }




            // Populating row 5 and adding it to the table
            Cell cell9 = new Cell();
            cell9.add("QR Code");
            table.addCell(cell9);

            // Creating the cell10
            Cell cell10 = new Cell();

            String url = "https://technologykings.co.za/" +
                    String.valueOf(customerTickets.getUserId().getUserId()) + "/" + customerTickets.getReferenceNumber() +
                    "/" + qrCodeImage.getName();
            System.out.println(url);

            // Creating an ImageData object
            ImageData data = ImageDataFactory.create(url);

            // Creating the image
            Image img = new Image(data);
            img.scaleAbsolute(100f, 100f);
            // Adding image to the cell10
            cell10.add(img.setAutoScale(true));

            // Adding cell110 to the table
            table.addCell(cell10);

            // Adding Table to document
            doc.add(table);

            // Closing the document
            doc.close();

            System.out.println("Image added to table successfully..");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return barCodePdf;
    }


}

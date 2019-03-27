package co.za.quickbuyticketcomponent.services;

import co.za.quickbuyticketcomponent.modals.CustomerTickets;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
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

    public HashMap<String,File> createQrCodeServices(CustomerTickets customerTickets) throws IOException, DocumentException {

        File qrCodeFile = createQRCodeByReferenceNumber(customerTickets.getReferenceNumber());
        File mailAttachment = createMailAttachment(customerTickets,qrCodeFile);
        HashMap<String,File> map =new HashMap<>();
        map.put("pdf",mailAttachment);
        map.put("qrcode",qrCodeFile);
        logger.info("Pdf :" + mailAttachment.getAbsolutePath());
        logger.info("png :" + qrCodeFile.getAbsolutePath());
        ftpServices.uploadQRCodeToServer(customerTickets, qrCodeFile, mailAttachment);
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

    private File createMailAttachment(CustomerTickets customerTickets,File qrCodeImage) throws IOException {
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
}

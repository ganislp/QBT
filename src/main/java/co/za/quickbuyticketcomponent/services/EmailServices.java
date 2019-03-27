package co.za.quickbuyticketcomponent.services;

import co.za.quickbuyticketcomponent.modals.CustomerTickets;
import co.za.quickbuyticketcomponent.payload.Mail;
import co.za.quickbuyticketcomponent.utils.FTPConfigProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmailServices {

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private SpringTemplateEngine templateEngine;

    @Autowired
    FTPConfigProperties ftpConfigProperties;

    Logger logger = LoggerFactory.getLogger(CustomerTicketsService.class);


    private void sendSimpleMessage(Mail mail,File pdf) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());
//        helper.addAttachment("YourTickets.png",pdf);
        logger.info("print value {}", mail.getModel().get("name"));
        Context context = new Context();
        context.setVariables(mail.getModel());
        String html = templateEngine.process("email-template", context);

        helper.setTo(mail.getTo());
        helper.setText(html, true);
        helper.setSubject(mail.getSubject());
        helper.setFrom(mail.getFrom());
        emailSender.send(message);
        logger.info("Email Sent Successfully");
    }

    public void sendEmailToClient(CustomerTickets customerTickets, HashMap<String, File> attachments) throws MessagingException {
        Mail mail = new Mail();
        mail.setFrom(ftpConfigProperties.getUser());
        mail.setTo(customerTickets.getUserId().getEmail());
        mail.setSubject("Tickets Confirmation");
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("name", toCamelCase(customerTickets.getUserId().getFirstName() + " " + customerTickets.getUserId().getLastName()));

        data.put("combo", customerTickets.getComboTickets());
        data.put("cricket", customerTickets.getCricketTickets());
        data.put("cultural", customerTickets.getCulturalTickets());

        data.put("qrcode", "http://technologykings.co.za/" + customerTickets.getUserId().getUserId() + "/" + customerTickets.getReferenceNumber() + "/" + attachments.get("qrcode").getName());
        mail.setModel(data);
        sendSimpleMessage(mail,attachments.get("Pdf"));
        System.out.println("http://technologykings.co.za/" + customerTickets.getUserId().getUserId() + "/" + customerTickets.getReferenceNumber() + "/" + attachments.get("qrcode").getName());
    }

    private String toCamelCase(String fullname) {
        if (fullname == null)
            return null;

        final StringBuilder ret = new StringBuilder(fullname.length());

        for (final String word : fullname.split(" ")) {
            if (!word.isEmpty()) {
                ret.append(Character.toUpperCase(word.charAt(0)));
                ret.append(word.substring(1).toLowerCase());
            }
            if (!(ret.length() == fullname.length()))
                ret.append(" ");
        }

        return ret.toString();
    }
}
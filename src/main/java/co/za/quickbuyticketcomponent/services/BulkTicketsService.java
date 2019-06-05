package co.za.quickbuyticketcomponent.services;


import co.za.quickbuyticketcomponent.controllers.UserProfileController;
import co.za.quickbuyticketcomponent.modals.BulkTickets;
import co.za.quickbuyticketcomponent.modals.CustomerTickets;
import co.za.quickbuyticketcomponent.modals.TicketTypes;
import co.za.quickbuyticketcomponent.modals.UserProfile;
import co.za.quickbuyticketcomponent.payload.BulkTicketsTO;
import co.za.quickbuyticketcomponent.repositories.BulkTicketsRepository;
import co.za.quickbuyticketcomponent.repositories.CustomerTicketsRepository;
import co.za.quickbuyticketcomponent.repositories.TicketTypeRepository;
import co.za.quickbuyticketcomponent.repositories.UserProfileRepository;
import com.itextpdf.text.DocumentException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.util.Date;
import java.util.HashMap;

@Component
public class BulkTicketsService {

    Logger logger = LoggerFactory.getLogger(BulkTicketsService.class);

    @Autowired
    BulkTicketsRepository bulkTicketsRepository;

    @Autowired
    TicketTypeRepository ticketTypeRepository;

    @Autowired
    CustomerTicketsRepository customerTicketsRepository;


    @Autowired
    UserProfileRepository userProfileRepository;

    @Autowired
    QRCodeServices qrCodeServices;

    public void createTickets(BulkTicketsTO bulkTicketsTO) {

        UserProfile userProfile = userProfileRepository.findByUserId(bulkTicketsTO.getUserProfileId());
        TicketTypes ticketTypes = ticketTypeRepository.findOne(1);
        if (userProfile != null) {
            for (int i = 1; i <= bulkTicketsTO.getNumberOfTickets(); i++) {

                BulkTickets bulkTickets = new BulkTickets();
                bulkTickets.setUser_profile_id(userProfile);
                bulkTickets.setReference_number(RandomStringUtils.randomAlphanumeric(7).toUpperCase());
                bulkTickets.setAmount_paid(0);
                bulkTickets.setResponsible_person(userProfile.getFirstName());
                bulkTickets.setFilename(RandomStringUtils.randomAlphanumeric(10));
                bulkTickets.setTicketTypes(ticketTypes);
                bulkTicketsRepository.save(bulkTickets);


                CustomerTickets customerTickets = new CustomerTickets();
                customerTickets.setUserId(userProfile);
                customerTickets.setGrasstickets(1);
//                customerTickets.setCulturalTickets(1);
//                customerTickets.setCricketTickets(1);
//                customerTickets.setComboTickets(1);
                customerTickets.setReferenceNumber(bulkTickets.getReference_number());
                customerTickets.setTotalPrice(new BigDecimal(100));
                customerTickets.setCreatedDate(new Date());
                try {
                    HashMap<String, Object> attachments = qrCodeServices.createQrCodeServices(customerTickets);
                    File pdf = (File) attachments.get("pdf");
                    customerTickets.setPdfFilename(pdf.getName());
                    customerTicketsRepository.save(customerTickets);

                    new File("/Users/vinayvadlamuri/Downloads/QBT-ManualTickets/" + customerTickets.getUserId().getUserId()).mkdirs();
                    File dest = new File("/Users/vinayvadlamuri/Downloads/QBT-ManualTickets/Leela/cultural/" + customerTickets.getUserId().getUserId() + "/" + String.valueOf(i) + "-" + customerTickets.getUserId().getFirstName().toUpperCase()+" "+customerTickets.getUserId().getLastName().toUpperCase() + "-" + customerTickets.getReferenceNumber()+".pdf");

                    try {
                        FileUtils.copyFile(pdf, dest);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (DocumentException e) {
                    e.printStackTrace();
                }

            }
        }
    }

}

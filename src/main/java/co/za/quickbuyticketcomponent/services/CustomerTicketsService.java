package co.za.quickbuyticketcomponent.services;

import co.za.quickbuyticketcomponent.modals.CustomerTickets;
import co.za.quickbuyticketcomponent.modals.CustomerTransactionDetails;
import co.za.quickbuyticketcomponent.modals.UserProfile;
import co.za.quickbuyticketcomponent.payload.CompletePaymentProcessTO;
import co.za.quickbuyticketcomponent.payload.CustomerTicketsResponseTO;
import co.za.quickbuyticketcomponent.payload.CustomerTicketsTO;
import co.za.quickbuyticketcomponent.repositories.CustomerTicketsRepository;
import co.za.quickbuyticketcomponent.repositories.TransactionDetailsRepository;
import co.za.quickbuyticketcomponent.repositories.UserProfileRepository;
import co.za.quickbuyticketcomponent.utils.FTPConfigProperties;
import com.itextpdf.text.DocumentException;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;

import static co.za.quickbuyticketcomponent.utils.Messages.USER_NOT_FOUND;

@Component
public class CustomerTicketsService {

    Logger logger = LoggerFactory.getLogger(CustomerTicketsService.class);

    @Autowired
    CustomerTicketsRepository customerTicketsRepository;

    @Autowired
    UserProfileRepository userProfileRepository;

    @Autowired
    SecurityTokenService securityTokenService;

    @Autowired
    TransactionDetailsRepository transactionDetailsRepository;

    @Autowired
    EmailServices emailServices;

    @Autowired
    FTPConfigProperties ftpConfigProperties;

    public CustomerTicketsResponseTO saveCustomerTicket(CustomerTicketsTO customerTicketsTO) {
        logger.info("customerTicketsTOs {} ", customerTicketsTO);
        UserProfile userProfile = userProfileRepository.findByEmail(customerTicketsTO.getCustomerEmail());
        logger.info("found user profile  {} for user id {}", customerTicketsTO.getCustomerEmail(),userProfile);
        CustomerTickets customerTickets = new CustomerTickets();
        customerTickets.setComboTickets(customerTicketsTO.getComboTickets());
        customerTickets.setCricketTickets(customerTicketsTO.getCricketTickets());
        customerTickets.setCulturalTickets(customerTicketsTO.getCulturalTickets());
        customerTickets.setUserId(userProfile);
        customerTickets.setPaymentReceived(0);
        customerTickets.setEmailSent(0);
        customerTickets.setCreatedDate(new Date());
        customerTickets.setReferenceNumber(RandomStringUtils.randomAlphanumeric(7).toUpperCase());
        customerTickets.setTotalPrice(calculateTotalPriceOfTickets(customerTickets));
        logger.info("before save {} ", customerTickets);
        customerTicketsRepository.save(customerTickets);
        return buildCustomerTicketsResponse(customerTickets, userProfile);
    }

    private CustomerTicketsResponseTO buildCustomerTicketsResponse(CustomerTickets customerTickets, UserProfile userProfile) {
        CustomerTicketsResponseTO customerTicketsResponseTO = new CustomerTicketsResponseTO();
        customerTicketsResponseTO.setItemName("Cricket/Cultural Event");
        customerTicketsResponseTO.setItemDescription("Combo " + customerTickets.getComboTickets() + " -  Cricket " + customerTickets.getCricketTickets() + " - " + "Cultural " + customerTickets.getCulturalTickets());
        customerTicketsResponseTO.setReferenceNumber(customerTickets.getReferenceNumber());
        customerTicketsResponseTO.setCustomerTicketId(customerTickets.getCustomerTicketId());
        customerTicketsResponseTO.setUserId(userProfile.getUserId());
        customerTicketsResponseTO.setTotalPrice(customerTickets.getTotalPrice());
        customerTicketsResponseTO.setFirstName(userProfile.getFirstName());
        customerTicketsResponseTO.setLastName(userProfile.getLastName());
        customerTicketsResponseTO.setEmailAddress(userProfile.getEmail());
        return customerTicketsResponseTO;
    }

    private BigDecimal calculateTotalPriceOfTickets(CustomerTickets customerTickets) {
        BigDecimal comboTickets = new BigDecimal(customerTickets.getComboTickets() * 400);
        BigDecimal cricket = new BigDecimal(customerTickets.getCricketTickets() * 250);
        BigDecimal cultural = new BigDecimal(customerTickets.getCulturalTickets() * 200);
        return comboTickets.add(cricket).add(cultural);
    }


    @Autowired
    QRCodeServices qrCodeServices;

    public void completePaymentProcess(CompletePaymentProcessTO completePaymentProcessTO) throws MessagingException {

        UserProfile userProfile = userProfileRepository.findByEmail(completePaymentProcessTO.getEmail());
        if (userProfile != null) {
            CustomerTickets customerTickets = customerTicketsRepository.findByReferenceNumber(completePaymentProcessTO.getReferenceNumber());
            try {
                HashMap attachments = qrCodeServices.createQrCodeServices(customerTickets);
                emailServices.sendEmailToClient(customerTickets, attachments);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (DocumentException e) {
                e.printStackTrace();
            }

//            if (customerTickets != null) {
//                if (userProfile.getUserId() == customerTickets.getUserId().getUserId()) {
//                    CustomerTransactionDetails customerTransactionDetails = transactionDetailsRepository.findByCustomStr1(completePaymentProcessTO.getReferenceNumber());
////                    if (new BigDecimal(customerTransactionDetails.getAmountGross()).equals(customerTickets.getTotalPrice())) {
//                        if (customerTransactionDetails.getPaymentStatus().equalsIgnoreCase("Complete")) {
//                            customerTickets.setPaymentReceived(1);
//                            logger.info("Payment Complted Successfully");
//
//                        }
////                    }
//                }
//            }
        } else {
            throw new IllegalArgumentException(USER_NOT_FOUND);
        }
    }


}

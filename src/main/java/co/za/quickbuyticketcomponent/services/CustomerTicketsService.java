package co.za.quickbuyticketcomponent.services;

import co.za.quickbuyticketcomponent.modals.CustomerTickets;
import co.za.quickbuyticketcomponent.modals.CustomerTransactionDetails;
import co.za.quickbuyticketcomponent.modals.UserProfile;
import co.za.quickbuyticketcomponent.modals.VerifiedTickets;
import co.za.quickbuyticketcomponent.payload.*;
import co.za.quickbuyticketcomponent.repositories.CustomerTicketsRepository;
import co.za.quickbuyticketcomponent.repositories.TransactionDetailsRepository;
import co.za.quickbuyticketcomponent.repositories.UserProfileRepository;
import co.za.quickbuyticketcomponent.repositories.VerifiedTicketsRepository;
import co.za.quickbuyticketcomponent.utils.FTPConfigProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.text.DocumentException;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static co.za.quickbuyticketcomponent.utils.Messages.USER_NOT_FOUND;

@Component
public class CustomerTicketsService {

    Logger logger = LoggerFactory.getLogger(CustomerTicketsService.class);

    @Autowired
    CustomerTicketsRepository customerTicketsRepository;

    @Autowired
    VerifiedTicketsRepository verifiedTicketsRepository;

    @Autowired
    UserProfileRepository userProfileRepository;

    @Autowired
    SecurityTokenService securityTokenService;

    @Autowired
    TransactionDetailsRepository transactionDetailsRepository;

    @Autowired
    EmailServices emailServices;

    @Autowired
    BulkSmsService bulkSmsService;

    @Autowired
    FTPConfigProperties ftpConfigProperties;

    public CustomerTicketsResponseTO saveCustomerTicket(CustomerTicketsTO customerTicketsTO) {
        logger.info("customerTicketsTOs {} ", customerTicketsTO);
        UserProfile userProfile = userProfileRepository.findByEmail(customerTicketsTO.getCustomerEmail());
        logger.info("found user profile  {} for user id {}", customerTicketsTO.getCustomerEmail(), userProfile);
        CustomerTickets customerTickets = new CustomerTickets();
        customerTickets.setComboTickets(customerTicketsTO.getComboTickets());
        customerTickets.setCricketTickets(customerTicketsTO.getCricketTickets());
        customerTickets.setCulturalTickets(customerTicketsTO.getCulturalTickets());
        customerTickets.setKidsTickets(customerTicketsTO.getKidsTickets());
        customerTickets.setGrasstickets(customerTicketsTO.getGrassTickets());
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
        BigDecimal comboTickets = new BigDecimal(customerTickets.getComboTickets() * 500);
        BigDecimal cricket = new BigDecimal(customerTickets.getCricketTickets() * 200);
        BigDecimal cultural = new BigDecimal(customerTickets.getCulturalTickets() * 200);
        BigDecimal grass = new BigDecimal(customerTickets.getGrasstickets() * 100);
        return comboTickets.add(cricket).add(cultural).add(grass);
    }


    @Autowired
    QRCodeServices qrCodeServices;

    public CompletePaymentResponseTO completePaymentProcess(CompletePaymentProcessTO completePaymentProcessTO) throws MessagingException {
        CompletePaymentResponseTO completePaymentResponseTO = new CompletePaymentResponseTO();
        UserProfile userProfile = userProfileRepository.findByEmail(completePaymentProcessTO.getEmail());
        if (userProfile != null) {
            CustomerTickets customerTickets = customerTicketsRepository.findByReferenceNumber(completePaymentProcessTO.getReferenceNumber());


            if (customerTickets != null) {
                if (userProfile.getUserId() == customerTickets.getUserId().getUserId()) {
                    CustomerTransactionDetails customerTransactionDetails = transactionDetailsRepository.findByCustomStr1(completePaymentProcessTO.getReferenceNumber());
                    if (customerTransactionDetails != null) {
                        if (new BigDecimal(customerTransactionDetails.getAmountGross()).equals(customerTickets.getTotalPrice())) {
                            if (customerTransactionDetails.getPaymentStatus().equalsIgnoreCase("Complete")) {
                                customerTickets.setPaymentReceived(1);
                                customerTicketsRepository.save(customerTickets);
                                logger.info("Payment Completed Successfully");
                                try {
                                    HashMap<String, Object> attachments = qrCodeServices.createQrCodeServices(customerTickets);
                                    emailServices.sendEmailToClient(customerTickets, attachments);
                                    bulkSmsService.sendReferenceNotification(notificationSMSRequest(customerTickets.getUserId().getMobile(), customerTickets.getReferenceNumber()));
                                    logger.info("pdfname {}", attachments.get("pdfname").toString());
                                    logger.info("qrcode {}", attachments.get("qrcodename").toString());
                                    completePaymentResponseTO.setPdfReference("https://technologykings.co.za/" + customerTickets.getUserId().getUserId() + "/" + customerTickets.getReferenceNumber() + "/" + attachments.get("pdfname").toString());
                                    completePaymentResponseTO.setQrCodeRef("https://technologykings.co.za/" + customerTickets.getUserId().getUserId() + "/" + customerTickets.getReferenceNumber() + "/" + attachments.get("qrcodename").toString());

                                    return completePaymentResponseTO;
                                } catch (IOException e) {
                                    e.printStackTrace();
                                } catch (DocumentException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                }
            }
        } else {
            throw new IllegalArgumentException(USER_NOT_FOUND);
        }

        return null;
    }

    private BulkSMSRequest notificationSMSRequest(String cellphone, String reference) {


        BulkSMSRequest bulkSMSRequest = new BulkSMSRequest();
        SmsFromAddress smsFromAddress = new SmsFromAddress();
        smsFromAddress.setAddress("1111111");
        smsFromAddress.setType("INTERNATIONAL");
        bulkSMSRequest.setFrom(smsFromAddress);
        System.out.println("formatCellNumber(cellphone) :" + formatCellNumber(cellphone));
        System.out.println("formatCellNumber(cellphone) :" + "QuickBuyTicket :  Your reference number " + reference);
        List<ToAddressDetail> toAddress = new ArrayList<>();
        toAddress.add(new ToAddressDetail("INTERNATIONAL", formatCellNumber(cellphone), null));
        bulkSMSRequest.setTo(toAddress);
        bulkSMSRequest.setBody("Thanks for purchasing the tickets from QuickBuyTicket " +
                " Your reference number " + reference);
        bulkSMSRequest.setRoutingGroup("ECONOMY");
        bulkSMSRequest.setEncoding("TEXT");
        bulkSMSRequest.setLongMessageMaxParts(99);
        bulkSMSRequest.setUserSuppliedId("submission-12765");
        bulkSMSRequest.setProtocolId("IMPLICIT");
        bulkSMSRequest.setMessageClass("SIM_SPECIFIC");
        bulkSMSRequest.setDeliveryReports("ALL");


        return bulkSMSRequest;
    }

    private String formatCellNumber(String cellphone) {
        return "+27".concat(cellphone.substring(1));
    }


    public String checkReferenceNumber(String referenceNumber) {
        if(customerTicketsRepository.findByReferenceNumber(referenceNumber) != null){

            if (verifiedTicketsRepository.findByReferenceNumber(referenceNumber) == null) {
                verifiedTicketsRepository.save(new VerifiedTickets(referenceNumber, new Date()));
                return "1";
            }
            return "2";
        }
        else {
            return "0";
        }

    }

}

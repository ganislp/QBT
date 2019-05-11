package co.za.quickbuyticketcomponent.controllers;


import co.za.quickbuyticketcomponent.exception.QuickBuyBusinessException;
import co.za.quickbuyticketcomponent.modals.RestResponse;
import co.za.quickbuyticketcomponent.modals.UserProfile;
import co.za.quickbuyticketcomponent.payload.*;
import co.za.quickbuyticketcomponent.services.BulkSmsService;
import co.za.quickbuyticketcomponent.services.CustomerTicketsService;
import co.za.quickbuyticketcomponent.services.SecurityTokenService;
import co.za.quickbuyticketcomponent.services.UserProfileService;
import co.za.quickbuyticketcomponent.utils.ResourceUtil;
import co.za.quickbuyticketcomponent.utils.ResponseBuilderAgent;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

@RestController
@CrossOrigin("*")
public class UserProfileController {

    Logger logger = LoggerFactory.getLogger(UserProfileController.class);

    @Autowired
    ResponseBuilderAgent responseBuilderAgent;

    @Autowired
    UserProfileService userProfileService;

    @Autowired
    SecurityTokenService securityTokenService;

    @Autowired
    ResourceUtil resourceUtil;

    @Autowired
    CustomerTicketsService customerTicketsService;

    @Autowired
    BulkSmsService bulkSmsService;

    RestResponse errorResponse = null;

    @RequestMapping(method = RequestMethod.POST, value = "/authorizeUser")
    public ResponseEntity<?> sighupUserProfile(HttpServletRequest request, @RequestBody UserProfileTO userProfileTO) {
        try {
            logger.info("authorizing user with  ", userProfileTO);

            UserProfile userProfile = userProfileService.authorizeUser(userProfileTO);

            return new ResponseEntity(securityTokenService.generateSecurityToken(userProfile), HttpStatus.OK);
        } catch (QuickBuyBusinessException exception) {
            errorResponse = responseBuilderAgent.createFailureResponse(exception, request.getRequestURI().toString(), HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @RequestMapping(method = RequestMethod.POST, value = "/signUp")
    public ResponseEntity<?> signUp(HttpServletRequest request, @RequestBody SignUpUserProfileTO signUpUserProfileTO) throws QuickBuyBusinessException {
        logger.info("Signing up new user {}", signUpUserProfileTO);
        try {
            UserProfile userProfile = userProfileService.sighupUserProfile(signUpUserProfileTO);
            return new ResponseEntity(securityTokenService.generateSecurityToken(userProfile), HttpStatus.OK);
        } catch (QuickBuyBusinessException exception) {
            errorResponse = responseBuilderAgent.createFailureResponse(exception, request.getRequestURI().toString(), HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    private static final String PAYFAST_VALIDATE_URL_TEST = "https://payfast.co.za/eng/query/validate";

    @RequestMapping(method = RequestMethod.POST, value = "/notify")
    public void notify(HttpServletRequest request) {
        Enumeration en = request.getParameterNames();
        List nvps = new ArrayList();
        while (en.hasMoreElements()) {
            String parm = en.nextElement().toString();
            String value = request.getParameter(parm);
            if (!parm.equals("signature"))
                nvps.add(new BasicNameValuePair(parm, value));
            logger.info("Key {} Value  ------ {} ", parm, value);
        }
        logger.info("Parameter list  ------ {} ", nvps);
        boolean valid = isValidateData(PAYFAST_VALIDATE_URL_TEST, nvps);
        logger.info("valid  ------ {} ", valid);
    }


    private boolean isValidateData(String site, List list) {
        StringBuilder validResponse = new StringBuilder();
        InputStream instream = null;
        try {
            DefaultHttpClient httpclient = new DefaultHttpClient();
            HttpPost httpost = new HttpPost(site);
            httpost.setEntity(new UrlEncodedFormEntity(list, HTTP.UTF_8));
            HttpResponse response = httpclient.execute(httpost);
            HttpEntity entity = response.getEntity();

            if (entity != null) {
                instream = entity.getContent();
                int l;
                byte[] tmp = new byte[512];
                while ((l = instream.read(tmp)) != -1)
                    validResponse.append(new String(tmp, 0, l));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (instream != null) {
                try {
                    instream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        if (validResponse.toString().equals("VALID"))
            return true;
        else
            return false;
    }


    @RequestMapping(method = RequestMethod.POST, value = "/initiatePayment")
    public ResponseEntity<?> initatePayment(HttpServletRequest request, @RequestBody CustomerTicketsTO customerTicketsTO) throws QuickBuyBusinessException {
        logger.info("Customer Tickets  {}", customerTicketsTO);
        try {
            CustomerTicketsResponseTO customerTickets = customerTicketsService.saveCustomerTicket(customerTicketsTO);
            return new ResponseEntity(customerTickets, HttpStatus.OK);
        } catch (Exception exception) {
            exception.printStackTrace();
            errorResponse = responseBuilderAgent.createFailureResponse(exception, request.getRequestURI().toString(), HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/completePaymentProcess")
    public ResponseEntity<CompletePaymentResponseTO> completePaymentProcess(HttpServletRequest request, @RequestBody CompletePaymentProcessTO completePaymentProcessTO) throws QuickBuyBusinessException {
        logger.info("Complete Payment Process Received Object  {}", completePaymentProcessTO);
        try {
            CompletePaymentResponseTO completePaymentResponseTO = customerTicketsService.completePaymentProcess(completePaymentProcessTO);
            return new ResponseEntity(completePaymentResponseTO, HttpStatus.OK);
        } catch (Exception exception) {
            exception.printStackTrace();
            errorResponse = responseBuilderAgent.createFailureResponse(exception, request.getRequestURI().toString(), HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @RequestMapping(method = RequestMethod.POST, value = "/sms")
    public ResponseEntity<BulkSMSResponse> bulkSms(HttpServletRequest request, @RequestBody BulkSMSRequest[] completePaymentProcessTO) throws QuickBuyBusinessException {
        logger.info("Complete Payment Process Received Object  {}", completePaymentProcessTO);
        try {
            bulkSmsService.sendReferenceNotification(completePaymentProcessTO[0]);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception exception) {
            exception.printStackTrace();
            errorResponse = responseBuilderAgent.createFailureResponse(exception, request.getRequestURI().toString(), HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @RequestMapping("/hello")
    public ResponseEntity<UserProfileTO> sayHello() {
        return new ResponseEntity(new UserProfileTO("vinay","vinay"), HttpStatus.OK);
    }

}

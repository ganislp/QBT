package co.za.quickbuyticketcomponent.controllers;


import co.za.quickbuyticketcomponent.exception.QuickBuyBusinessException;
import co.za.quickbuyticketcomponent.modals.RestResponse;
import co.za.quickbuyticketcomponent.modals.UserProfile;
import co.za.quickbuyticketcomponent.payload.SignUpUserProfileTO;
import co.za.quickbuyticketcomponent.payload.UserProfileTO;
import co.za.quickbuyticketcomponent.services.SecurityTokenService;
import co.za.quickbuyticketcomponent.services.UserProfileService;
import co.za.quickbuyticketcomponent.utils.Messages;
import co.za.quickbuyticketcomponent.utils.ResourceUtil;
import co.za.quickbuyticketcomponent.utils.ResponseBuilderAgent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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
    RestResponse errorResponse = null;
    @RequestMapping(method = RequestMethod.POST, value = "/authorizeUser")
    public ResponseEntity<?> sighupUserProfile(HttpServletRequest request, @RequestBody UserProfileTO userProfileTO) {

        try {
            logger.info("authorizing user with  ", userProfileTO);
            UserProfile userProfile = userProfileService.authorizeUser(userProfileTO);
            return new ResponseEntity(securityTokenService.generateSecurityToken(userProfile), HttpStatus.OK);
        } catch ( QuickBuyBusinessException exception) {
           errorResponse = responseBuilderAgent.createFailureResponse(exception, request.getRequestURI().toString(), exception.getErrorCode().intValue());
            return new ResponseEntity(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/signUp")
    public ResponseEntity<?> signUp(HttpServletRequest request, @RequestBody SignUpUserProfileTO signUpUserProfileTO) throws QuickBuyBusinessException {
        logger.info("Signing up new user {}",signUpUserProfileTO);
        try {
        UserProfile userProfile = userProfileService.sighupUserProfile(signUpUserProfileTO);
            return new ResponseEntity<>(userProfile,HttpStatus.OK);

        } catch ( QuickBuyBusinessException exception) {
            errorResponse = responseBuilderAgent.createFailureResponse(exception, request.getRequestURI().toString(), exception.getErrorCode().intValue());
            return new ResponseEntity(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(method = RequestMethod.POST, value = "/notify")
    public void notify(HttpServletRequest request, @RequestBody Object object) {
        logger.info("notify value {}",object);
    }

}

package co.za.quickbuyticketcomponent.controllers;


import co.za.quickbuyticketcomponent.modals.RestResponse;
import co.za.quickbuyticketcomponent.modals.UserProfile;
import co.za.quickbuyticketcomponent.payload.SignUpUserProfileTO;
import co.za.quickbuyticketcomponent.payload.UserProfileTO;
import co.za.quickbuyticketcomponent.services.SecurityTokenService;
import co.za.quickbuyticketcomponent.services.UserProfileService;
import co.za.quickbuyticketcomponent.utils.Messages;
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

    @RequestMapping(method = RequestMethod.POST, value = "/authorizeUser")
    public ResponseEntity<?> sighupUserProfile(HttpServletRequest request, @RequestBody UserProfileTO userProfileTO) {
        logger.info("authorizing user with  ",userProfileTO);
        UserProfileTO userProfile = userProfileService.authorizeUser(userProfileTO);
            return new ResponseEntity<>(userProfile,HttpStatus.OK);

    }


    @RequestMapping(method = RequestMethod.POST, value = "/signUp")
    public ResponseEntity<?> signUp(HttpServletRequest request, @RequestBody SignUpUserProfileTO signUpUserProfileTO) {
        logger.info("Signing up new user {}",signUpUserProfileTO);
        UserProfileTO userProfile = userProfileService.sighupUserProfile(signUpUserProfileTO);
        if(userProfile !=null){
            return new ResponseEntity<>(userProfile,HttpStatus.OK);
        }
        throw new IllegalArgumentException("No user account found");
    }

    @RequestMapping(method = RequestMethod.POST, value = "/notify")
    public void notify(HttpServletRequest request, @RequestBody Object object) {
        logger.info("notify value {}",object);
    }

}

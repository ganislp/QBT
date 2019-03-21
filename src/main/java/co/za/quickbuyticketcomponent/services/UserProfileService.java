package co.za.quickbuyticketcomponent.services;

import co.za.quickbuyticketcomponent.modals.AccountType;
import co.za.quickbuyticketcomponent.modals.UserProfile;
import co.za.quickbuyticketcomponent.payload.SignUpUserProfileTO;
import co.za.quickbuyticketcomponent.payload.UserProfileTO;
import co.za.quickbuyticketcomponent.repositories.AccountTypeRepository;
import co.za.quickbuyticketcomponent.repositories.UserProfileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserProfileService {


    Logger logger = LoggerFactory.getLogger(UserProfileService.class);

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private AccountTypeRepository accountTypeRepository;

    public UserProfile saveUserProfile(UserProfile userProfile) {
        return userProfileRepository.save(userProfile);
    }

    public AccountType getAccountTypeById(int accountTypeId) {
        logger.info("accountTypeId  {}", accountTypeRepository.findByAccountTypeId(accountTypeId));
        return accountTypeRepository.findByAccountTypeId(accountTypeId);
    }

    public Long countUserProfileByEmail(String email) {
        return userProfileRepository.countUserProfileByEmail(email);
    }

    public UserProfileTO authorizeUser(UserProfileTO userProfileTO) {
        UserProfile userProfile = userProfileRepository.findByEmailAndPassword(userProfileTO.getEmail(), userProfileTO.getPassword());
        if (userProfile != null) {
            return mapUserProfileToResponse(userProfile);
        }
        throw new IllegalArgumentException("No user account found");
    }

    public UserProfileTO sighupUserProfile(SignUpUserProfileTO userProfileTO) {
        AccountType accountType = getAccountTypeById(userProfileTO.getAccountTypeId());
        if (accountType == null) {
            throw new IllegalArgumentException("Invalid Account Type Id");
        }
        if (countUserProfileByEmail(userProfileTO.getEmail()) == 0) {
            UserProfile userProfile = new UserProfile();
            userProfile.setFirstName(userProfileTO.getFirstName());
            userProfile.setLastName(userProfileTO.getLastName());
            userProfile.setEmail(userProfileTO.getEmail());
            userProfile.setPassword(userProfileTO.getPassword());
            userProfile.setMobile(userProfileTO.getMobile());
            userProfile.setCreatedAt(new Date());
            userProfile.setIsActive("T");
            userProfile.setAccountType(accountType);
            userProfile.setIsDeleted(0);
            UserProfile newUser = saveUserProfile(userProfile);
            return mapUserProfileToResponse(newUser);
        } else {
            throw new IllegalArgumentException("Email address already exists");
        }
    }

    public UserProfileTO mapUserProfileToResponse(UserProfile newUser) {
        UserProfileTO userProfile = new UserProfileTO();
        userProfile.setFirstName(newUser.getFirstName());
        userProfile.setLastName(newUser.getLastName());
        userProfile.setEmail(newUser.getEmail());
        userProfile.setMobile(newUser.getMobile());
        userProfile.setAccountTypeId(newUser.getAccountType().getAccountTypeId());
        userProfile.setAccessToken("jkiunsdjlkjhd2xdsds8932234");
        return userProfile;
    }

}

package co.za.quickbuyticketcomponent.services;

import co.za.quickbuyticketcomponent.exception.QuickBuyBusinessException;
import co.za.quickbuyticketcomponent.modals.AccountType;
import co.za.quickbuyticketcomponent.modals.UserProfile;
import co.za.quickbuyticketcomponent.payload.SignUpUserProfileTO;
import co.za.quickbuyticketcomponent.payload.UserProfileTO;
import co.za.quickbuyticketcomponent.repositories.AccountTypeRepository;
import co.za.quickbuyticketcomponent.repositories.UserProfileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.util.Date;

import static co.za.quickbuyticketcomponent.utils.QuickBuyMessageConstant.*;
import static co.za.quickbuyticketcomponent.utils.QuickBuyUtil.throwQuickBuyBusinessException;

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

    public UserProfile findAllByEmail(String email) {
        return userProfileRepository.findAllByEmail(email);
    }

    public AccountType getAccountTypeById(String accountTypeDesc) {
        logger.info("accountTypeId  {}", accountTypeRepository.findByAccountTypeDesc(accountTypeDesc));
        return accountTypeRepository.findByAccountTypeDesc(accountTypeDesc);
    }

    public Long countUserProfileByEmail(String email) {
        return userProfileRepository.countUserProfileByEmail(email);
    }

    public UserProfile findAllByEmailAndAccountType_AccountTypeDesc(String email, AccountType accountType ) {
        return userProfileRepository.findAllByEmailAndAccountType(email, accountType);
    }

    public UserProfile authorizeUser(UserProfileTO userProfileTO) throws QuickBuyBusinessException {

        UserProfile userProfile = userProfileRepository.findByEmailAndPassword(userProfileTO.getEmail(), userProfileTO.getPassword());
        if(userProfile == null){
            throwQuickBuyBusinessException(RESPONSE_BAD_CREDENTIALS_EXCEPTION);

        }


      return userProfile;
    }

    public UserProfile sighupUserProfile(SignUpUserProfileTO userProfileTO)throws QuickBuyBusinessException{
        AccountType accountType = new AccountType();
         accountType = getAccountTypeById(userProfileTO.getAccountTypeDesc());
        UserProfile newUser = new UserProfile();
        UserProfile userProfile = new UserProfile();
        if (accountType == null) {
            throwQuickBuyBusinessException(ACCOUNT_TYPE_INVALID_EXCEPTION);
        }
        else if(accountType.getAccountTypeDesc().equalsIgnoreCase("Guast")){
             userProfile = findAllByEmail(userProfileTO.getEmail());
            if(userProfile != null){
                UserProfile guastUserProfileExist = new UserProfile();
                userProfile.setModifiedAt(new Date());
                guastUserProfileExist =     saveUserProfile(userProfile);
                return guastUserProfileExist;
            }
            else {
                 userProfile = new UserProfile();
                userProfile.setFirstName(userProfileTO.getFirstName());
                userProfile.setLastName(userProfileTO.getLastName());
                userProfile.setEmail(userProfileTO.getEmail());
                userProfile.setPassword(userProfileTO.getPassword());
                userProfile.setMobile(userProfileTO.getMobile());
                userProfile.setCreatedAt(new Date());
                userProfile.setIsActive("T");
                userProfile.setAccountType(accountType);
                userProfile.setIsDeleted(0);
                newUser = saveUserProfile(userProfile);
            }




        }

        else if(accountType.getAccountTypeDesc().equalsIgnoreCase("Singup")) {
            accountType = getAccountTypeById(userProfileTO.getAccountTypeDesc());
            userProfile =  findAllByEmailAndAccountType_AccountTypeDesc(userProfileTO.getEmail(),accountType);
            if (countUserProfileByEmail(userProfileTO.getEmail()) == 0 && userProfile == null) {
                userProfile = new UserProfile();
                userProfile.setFirstName(userProfileTO.getFirstName());
                userProfile.setLastName(userProfileTO.getLastName());
                userProfile.setEmail(userProfileTO.getEmail());
                userProfile.setPassword(userProfileTO.getPassword());
                userProfile.setMobile(userProfileTO.getMobile());
                userProfile.setCreatedAt(new Date());
                userProfile.setIsActive("T");
                userProfile.setAccountType(accountType);
                userProfile.setIsDeleted(0);
                newUser = saveUserProfile(userProfile);

            } else if (countUserProfileByEmail(userProfileTO.getEmail()) > 0 && userProfile != null) {
               accountType = getAccountTypeById(userProfileTO.getAccountTypeDesc());
                userProfile.setAccountType(accountType);
                userProfile.setModifiedAt(new Date());
                newUser = saveUserProfile(userProfile);


            }

         else if (countUserProfileByEmail(userProfileTO.getEmail()) > 0 && userProfile == null) {

                AccountType accountTypenew = getAccountTypeById(userProfileTO.getAccountTypeDesc());
            userProfile.setAccountType(accountTypenew);
            userProfile.setModifiedAt(new Date());
            newUser = saveUserProfile(userProfile);
            throwQuickBuyBusinessException(ALREADY_USER_EXIST);

        }
            else {
                throwQuickBuyBusinessException(ALREADY_USER_EXIST);
            }
        }
        return newUser;
    }


    public UserProfileTO mapUserProfileToResponse(UserProfile newUser) {
        UserProfileTO userProfile = new UserProfileTO();
       // userProfile.setFirstName(newUser.getFirstName());
       // userProfile.setLastName(newUser.getLastName());
       // userProfile.setEmail(newUser.getEmail());
       // userProfile.setMobile(newUser.getMobile());
       // userProfile.setAccountTypeDesc(newUser.getAccountType().getAccountTypeId());
        //userProfile.setAccessToken("jkiunsdjlkjhd2xdsds8932234");
        return userProfile;
    }

}

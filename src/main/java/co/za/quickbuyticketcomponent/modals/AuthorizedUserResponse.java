package co.za.quickbuyticketcomponent.modals;

import java.util.Date;

/**
 * Created by gVadlamuri on 1/18/2018.
 */
public class AuthorizedUserResponse {

    private String email;
    private String accountType;
    private String isActive;
    private String access_token;

    private Date expireTime;

    public AuthorizedUserResponse() {
    }

    public AuthorizedUserResponse( String email,String accountType,String isActive, String access_token, Date expireTime) {

        this.email = email;
        this.setAccountType(accountType);
        this.isActive = isActive;
        this.access_token = access_token;
        this.expireTime = expireTime;
    }




    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }


    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
}



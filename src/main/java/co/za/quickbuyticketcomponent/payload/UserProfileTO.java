package co.za.quickbuyticketcomponent.payload;

import java.io.Serializable;


public class UserProfileTO extends SignUpUserProfileTO implements Serializable{

    private String accessToken;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}

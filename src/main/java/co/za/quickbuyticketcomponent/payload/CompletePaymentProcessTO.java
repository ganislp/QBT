package co.za.quickbuyticketcomponent.payload;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class CompletePaymentProcessTO implements Serializable{

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("referenceNumber")
    private String referenceNumber;

    @JsonProperty("email")
    private String email;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public CompletePaymentProcessTO() {
    }

    @Override
    public String toString() {
        return "CompletePaymentProcessTO{" +
                "accessToken='" + accessToken + '\'' +
                ", referenceNumber='" + referenceNumber + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public CompletePaymentProcessTO(String accessToken, String referenceNumber, String email) {
        this.accessToken = accessToken;
        this.referenceNumber = referenceNumber;
        this.email = email;
    }
}

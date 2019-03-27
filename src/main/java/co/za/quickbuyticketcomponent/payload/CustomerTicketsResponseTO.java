package co.za.quickbuyticketcomponent.payload;

import co.za.quickbuyticketcomponent.modals.UserProfile;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by gVadlamuri on 12/28/2017.
 */

public class CustomerTicketsResponseTO implements java.io.Serializable {

    @JsonProperty("item_name")
    private String itemName;

    @JsonProperty("item_description")
    private String itemDescription;

    @JsonProperty("custom_str1")
    private String referenceNumber;

    @JsonProperty("custom_int1")
    private int customerTicketId;

    @JsonProperty("custom_int2")
    private int userId;

    @JsonProperty("custom_int3")
    private BigDecimal totalPrice;

    @JsonProperty("name_first")
    private String firstName;

    @JsonProperty("name_last")
    private String lastName;


    @JsonProperty("email_address")
    private String emailAddress;


    public CustomerTicketsResponseTO() {

    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public int getCustomerTicketId() {
        return customerTicketId;
    }

    public void setCustomerTicketId(int customerTicketId) {
        this.customerTicketId = customerTicketId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}
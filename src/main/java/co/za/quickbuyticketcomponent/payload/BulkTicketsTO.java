package co.za.quickbuyticketcomponent.payload;

import co.za.quickbuyticketcomponent.modals.UserProfile;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * Created by gVadlamuri on 12/28/2017.
 */

public class BulkTicketsTO extends CustomerTransactionDetailsTO implements java.io.Serializable {


    @JsonProperty("userProfileId")
    private int userProfileId;
    @JsonProperty("numberOfTickets")
    private int numberOfTickets;

    public int getUserProfileId() {
        return userProfileId;
    }

    public void setUserProfileId(int userProfileId) {
        this.userProfileId = userProfileId;
    }

    public int getNumberOfTickets() {
        return numberOfTickets;
    }

    public void setNumberOfTickets(int numberOfTickets) {
        this.numberOfTickets = numberOfTickets;
    }

    @Override
    public String toString() {
        return "BulkTicketsTO{" +
                "userProfileId=" + userProfileId +
                ", numberOfTickets=" + numberOfTickets +
                '}';
    }
}
package co.za.quickbuyticketcomponent.payload;

import co.za.quickbuyticketcomponent.modals.UserProfile;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * Created by gVadlamuri on 12/28/2017.
 */

public class CustomerTicketsTO extends CustomerTransactionDetailsTO implements java.io.Serializable {


    @JsonProperty("customerTicketId")
    private int customerTicketId;
    @JsonProperty("UserId")
    private UserProfile UserId;
    @JsonProperty("ComboTickets")
    private int ComboTickets;
    @JsonProperty("access_token")
    private String access_token;
    @JsonProperty("CustomerEmail")
    private String CustomerEmail;
    @JsonProperty("CricketTickets")
    private int CricketTickets;
    @JsonProperty("CulturalTickets")
    private int CulturalTickets;
    @JsonProperty("KidsTickets")
    private int KidsTickets;

    @JsonProperty("GrassTickets")
    private int GrassTickets;
    @JsonProperty("PaymentReceived")
    private int PaymentReceived;
    @JsonProperty("EmailSent")
    private int EmailSent;
    @JsonProperty("ReferenceNumber")
    private String ReferenceNumber;
    @JsonProperty("PdfLink")
    private String PdfLink;
    @JsonProperty("pdfFilename")
    private String pdfFilename;
    @JsonProperty("CreatedDate")
    private Date CreatedDate;
    @JsonProperty("UpdatedDate")
    private Date UpdatedDate;


    public CustomerTicketsTO() {

    }

    public CustomerTicketsTO(UserProfile userProfile) {
        this.UserId = userProfile;
    }

    public CustomerTicketsTO(String access_token, String referenceNumber) {
        this.access_token = access_token;
        ReferenceNumber = referenceNumber;
    }

    public int getCustomerTicketId() {
        return customerTicketId;
    }

    public void setCustomerTicketId(int customerTicketId) {
        this.customerTicketId = customerTicketId;
    }

    public UserProfile getUserId() {
        return UserId;
    }

    public void setUserId(UserProfile userId) {
        UserId = userId;
    }

    public int getComboTickets() {
        return ComboTickets;
    }

    public void setComboTickets(int comboTickets) {
        ComboTickets = comboTickets;
    }

    public int getCricketTickets() {
        return CricketTickets;
    }

    public void setCricketTickets(int cricketTickets) {
        CricketTickets = cricketTickets;
    }

    public int getCulturalTickets() {
        return CulturalTickets;
    }

    public void setCulturalTickets(int culturalTickets) {
        CulturalTickets = culturalTickets;
    }

    public int getPaymentReceived() {
        return PaymentReceived;
    }

    public void setPaymentReceived(int paymentReceived) {
        PaymentReceived = paymentReceived;
    }

    public int getEmailSent() {
        return EmailSent;
    }

    public void setEmailSent(int emailSent) {
        EmailSent = emailSent;
    }

    public String getReferenceNumber() {
        return ReferenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        ReferenceNumber = referenceNumber;
    }

    public String getPdfLink() {
        return PdfLink;
    }

    public void setPdfLink(String pdfLink) {
        PdfLink = pdfLink;
    }

    public String getPdfFilename() {
        return pdfFilename;
    }

    public void setPdfFilename(String pdfFilename) {
        this.pdfFilename = pdfFilename;
    }

    public Date getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(Date createdDate) {
        CreatedDate = createdDate;
    }

    public Date getUpdatedDate() {
        return UpdatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        UpdatedDate = updatedDate;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getCustomerEmail() {
        return CustomerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        CustomerEmail = customerEmail;
    }

    public int getKidsTickets() {
        return KidsTickets;
    }

    public void setKidsTickets(int kidsTickets) {
        KidsTickets = kidsTickets;
    }

    public int getGrassTickets() {
        return GrassTickets;
    }

    public void setGrassTickets(int grassTickets) {
        GrassTickets = grassTickets;
    }

    @Override
    public String toString() {
        return "CustomerTicketsTO{" +
                "customerTicketId=" + customerTicketId +
                ", UserId=" + UserId +
                ", ComboTickets=" + ComboTickets +
                ", access_token='" + access_token + '\'' +
                ", CustomerEmail='" + CustomerEmail + '\'' +
                ", CricketTickets=" + CricketTickets +
                ", CulturalTickets=" + CulturalTickets +
                ", KidsTickets=" + KidsTickets +
                ", GrassTickets=" + GrassTickets +
                ", PaymentReceived=" + PaymentReceived +
                ", EmailSent=" + EmailSent +
                ", ReferenceNumber='" + ReferenceNumber + '\'' +
                ", PdfLink='" + PdfLink + '\'' +
                ", pdfFilename='" + pdfFilename + '\'' +
                ", CreatedDate=" + CreatedDate +
                ", UpdatedDate=" + UpdatedDate +
                '}';
    }
}
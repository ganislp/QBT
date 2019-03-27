package co.za.quickbuyticketcomponent.modals;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by gVadlamuri on 12/28/2017.
 */
@Entity
@Table(name="CustomerTickets")
public class CustomerTickets implements java.io.Serializable {


    private int customerTicketId;
    private UserProfile UserId;
    private int ComboTickets;
    private int CricketTickets;
    private int CulturalTickets;
    private int PaymentReceived;
    private int EmailSent;
    private String ReferenceNumber;
    private String PdfLink;
    private String pdfFilename;
    private Date CreatedDate;
    private Date UpdatedDate;
    private BigDecimal totalPrice;


    public CustomerTickets() {

    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CustomerTicketId", unique = true, nullable = false)
    public int getCustomerTicketId() {
        return customerTicketId;
    }

    public void setCustomerTicketId(int customerTicketId) {
        this.customerTicketId = customerTicketId;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "UserID", nullable = false)
    public UserProfile getUserId() {
        return UserId;
    }

    public void setUserId(UserProfile userId) {
        UserId = userId;
    }

    @Column(name = "ComboTickets")
    public int getComboTickets() {
        return ComboTickets;
    }

    public void setComboTickets(int comboTickets) {
        ComboTickets = comboTickets;
    }

    @Column(name = "CricketTickets")
    public int getCricketTickets() {
        return CricketTickets;
    }

    public void setCricketTickets(int cricketTickets) {
        CricketTickets = cricketTickets;
    }

    @Column(name = "CulturalTickets")
    public int getCulturalTickets() {
        return CulturalTickets;
    }

    public void setCulturalTickets(int culturalTickets) {
        CulturalTickets = culturalTickets;
    }

    @Column(name = "PaymentReceived")
    public int getPaymentReceived() {
        return PaymentReceived;
    }

    public void setPaymentReceived(int paymentReceived) {
        PaymentReceived = paymentReceived;
    }
    @Column(name = "EmailSent")
    public int getEmailSent() {
        return EmailSent;
    }

    public void setEmailSent(int emailSent) {
        EmailSent = emailSent;
    }

    @Column(name = "ReferenceNumber")
    public String getReferenceNumber() {
        return ReferenceNumber;
    }


    public void setReferenceNumber(String referenceNumber) {
        ReferenceNumber = referenceNumber;
    }

    @Column(name = "pdflink")
    public String getPdfLink() {
        return PdfLink;
    }

    public void setPdfLink(String pdfLink) {
        PdfLink = pdfLink;
    }

    @Column(name = "pdffilename")
    public String getPdfFilename() {
        return pdfFilename;
    }

    public void setPdfFilename(String pdfFilename) {
        this.pdfFilename = pdfFilename;
    }

    @Column(name = "createddate")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(Date createdDate) {
        CreatedDate = createdDate;
    }

    @Column(name = "updateddate")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getUpdatedDate() {
        return UpdatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        UpdatedDate = updatedDate;
    }

    @Column(name = "totalprice")
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public CustomerTickets(int customerTicketId, UserProfile userId, int comboTickets, int cricketTickets, int culturalTickets, int paymentReceived, int emailSent, String referenceNumber, String pdfLink, String pdfFilename, Date createdDate, Date updatedDate, BigDecimal totalPrice) {
        this.customerTicketId = customerTicketId;
        UserId = userId;
        ComboTickets = comboTickets;
        CricketTickets = cricketTickets;
        CulturalTickets = culturalTickets;
        PaymentReceived = paymentReceived;
        EmailSent = emailSent;
        ReferenceNumber = referenceNumber;
        PdfLink = pdfLink;
        this.pdfFilename = pdfFilename;
        CreatedDate = createdDate;
        UpdatedDate = updatedDate;
        this.totalPrice = totalPrice;
    }
}
package co.za.quickbuyticketcomponent.modals;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by gVadlamuri on 12/28/2017.
 */
@Entity
@Table(name="CustomerTransactionDetails")
public class CustomerTransactionDetails implements java.io.Serializable {


    private Integer TransactionDetailId;
    private Integer PayfastPaymentId;
    private String PaymentStatus;
    private String ItemName;
    private String ItemDescription;
    private String AmountGross;
    private String AmountFee;
    private String AmountNet;
    private String CustomStr1;
    private String CustomStr2;
    private String CustomStr3;
    private String CustomStr4;
    private String CustomStr5;
    private Integer CustomInt1;
    private Integer CustomInt2;
    private Integer CustomInt3;
    private Integer CustomInt4;
    private Integer CustomInt5;
    private String NameFirst;
    private String NameLast;
    private String EmailAddress;
    private Integer MerchantId;
    private String Signature;
    private UserProfile userProfile;
    private CustomerTickets customerticketid;



    public CustomerTransactionDetails() {

    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "m_payment_id", unique = true, nullable = false)
    public Integer getTransactionDetailId() {
        return TransactionDetailId;
    }

    public void setTransactionDetailId(Integer transactionDetailId) {
        TransactionDetailId = transactionDetailId;
    }

    @Column(name = "pf_payment_id")
    public Integer getPayfastPaymentId() {
        return PayfastPaymentId;
    }

    public void setPayfastPaymentId(Integer payfastPaymentId) {
        PayfastPaymentId = payfastPaymentId;
    }

    @Column(name = "payment_status")
    public String getPaymentStatus() {
        return PaymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        PaymentStatus = paymentStatus;
    }
    @Column(name = "item_name")
    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String itemName) {
        ItemName = itemName;
    }

    @Column(name = "item_description")
    public String getItemDescription() {
        return ItemDescription;
    }

    public void setItemDescription(String itemDescription) {
        ItemDescription = itemDescription;
    }

    @Column(name = "amount_gross")
    public String getAmountGross() {
        return AmountGross;
    }

    public void setAmountGross(String amountGross) {
        AmountGross = amountGross;
    }

    @Column(name = "amount_fee")
    public String getAmountFee() {
        return AmountFee;
    }

    public void setAmountFee(String amountFee) {
        AmountFee = amountFee;
    }

    @Column(name = "amount_net")
    public String getAmountNet() {
        return AmountNet;
    }

    public void setAmountNet(String amountNet) {
        AmountNet = amountNet;
    }

    @Column(name = "custom_str1")
    public String getCustomStr1() {
        return CustomStr1;
    }

    public void setCustomStr1(String customStr1) {
        CustomStr1 = customStr1;
    }

    @Column(name = "custom_str2")
    public String getCustomStr2() {
        return CustomStr2;
    }

    public void setCustomStr2(String customStr2) {
        CustomStr2 = customStr2;
    }
    @Column(name = "custom_str3")
    public String getCustomStr3() {
        return CustomStr3;
    }

    public void setCustomStr3(String customStr3) {
        CustomStr3 = customStr3;
    }

    @Column(name = "custom_str4")
    public String getCustomStr4() {
        return CustomStr4;
    }

    public void setCustomStr4(String customStr4) {
        CustomStr4 = customStr4;
    }

    @Column(name = "custom_str5")
    public String getCustomStr5() {
        return CustomStr5;
    }

    public void setCustomStr5(String customStr5) {
        CustomStr5 = customStr5;
    }

    @Column(name = "custom_int1")
    public Integer getCustomInt1() {
        return CustomInt1;
    }

    public void setCustomInt1(Integer customInt1) {
        CustomInt1 = customInt1;
    }

    @Column(name = "custom_int2")
    public Integer getCustomInt2() {
        return CustomInt2;
    }

    public void setCustomInt2(Integer customInt2) {
        CustomInt2 = customInt2;
    }

    @Column(name = "custom_int3")
    public Integer getCustomInt3() {
        return CustomInt3;
    }

    public void setCustomInt3(Integer customInt3) {
        CustomInt3 = customInt3;
    }

    @Column(name = "custom_int4")
    public Integer getCustomInt4() {
        return CustomInt4;
    }

    public void setCustomInt4(Integer customInt4) {
        CustomInt4 = customInt4;
    }

    @Column(name = "custom_int5")
    public Integer getCustomInt5() {
        return CustomInt5;
    }

    public void setCustomInt5(Integer customInt5) {
        CustomInt5 = customInt5;
    }

    @Column(name = "name_first")
    public String getNameFirst() {
        return NameFirst;
    }

    public void setNameFirst(String nameFirst) {
        NameFirst = nameFirst;
    }

    @Column(name = "name_last")
    public String getNameLast() {
        return NameLast;
    }

    public void setNameLast(String nameLast) {
        NameLast = nameLast;
    }

    @Column(name = "email_address")
    public String getEmailAddress() {
        return EmailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        EmailAddress = emailAddress;
    }

    @Column(name = "merchant_id")
    public Integer getMerchantId() {
        return MerchantId;
    }

    public void setMerchantId(Integer merchantId) {
        MerchantId = merchantId;
    }


    @Column(name = "signature")
    public String getSignature() {
        return Signature;
    }

    public void setSignature(String signature) {
        Signature = signature;
    }


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "UserID", nullable = false)
    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customerticketid", nullable = false)
    public CustomerTickets getCustomerticketid() {
        return customerticketid;
    }

    public void setCustomerticketid(CustomerTickets customerticketid) {
        this.customerticketid = customerticketid;
    }

    @Override
    public String toString() {
        return "CustomerTransactionDetails{" +
                "TransactionDetailId=" + TransactionDetailId +
                ", PayfastPaymentId=" + PayfastPaymentId +
                ", PaymentStatus='" + PaymentStatus + '\'' +
                ", ItemName='" + ItemName + '\'' +
                ", ItemDescription='" + ItemDescription + '\'' +
                ", AmountGross='" + AmountGross + '\'' +
                ", AmountFee='" + AmountFee + '\'' +
                ", AmountNet='" + AmountNet + '\'' +
                ", CustomStr1='" + CustomStr1 + '\'' +
                ", CustomStr2='" + CustomStr2 + '\'' +
                ", CustomStr3='" + CustomStr3 + '\'' +
                ", CustomStr4='" + CustomStr4 + '\'' +
                ", CustomStr5='" + CustomStr5 + '\'' +
                ", CustomInt1=" + CustomInt1 +
                ", CustomInt2=" + CustomInt2 +
                ", CustomInt3=" + CustomInt3 +
                ", CustomInt4=" + CustomInt4 +
                ", CustomInt5=" + CustomInt5 +
                ", NameFirst='" + NameFirst + '\'' +
                ", NameLast='" + NameLast + '\'' +
                ", EmailAddress='" + EmailAddress + '\'' +
                ", MerchantId=" + MerchantId +
                ", Signature='" + Signature + '\'' +
                ", userProfile=" + userProfile +
                ", customerticketid=" + customerticketid +
                '}';
    }
}
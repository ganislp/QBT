package co.za.quickbuyticketcomponent.payload;

import co.za.quickbuyticketcomponent.modals.CustomerTickets;
import co.za.quickbuyticketcomponent.modals.UserProfile;

import javax.persistence.*;

/**
 * Created by gVadlamuri on 12/28/2017.
 */

public class CustomerTransactionDetailsTO implements java.io.Serializable {


    private int TransactionDetailId;
    private int PayfastPaymentId;
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
    private int CustomInt1;
    private int CustomInt2;
    private int CustomInt3;
    private int CustomInt4;
    private int CustomInt5;
    private String NameFirst;
    private String NameLast;
    private String EmailAddress;
    private int MerchantId;
    private String Signature;
    private UserProfile userProfile;
    private CustomerTickets customerticketid;



    public CustomerTransactionDetailsTO() {

    }



    public int getTransactionDetailId() {
        return TransactionDetailId;
    }

    public void setTransactionDetailId(int transactionDetailId) {
        TransactionDetailId = transactionDetailId;
    }

    public int getPayfastPaymentId() {
        return PayfastPaymentId;
    }

    public void setPayfastPaymentId(int payfastPaymentId) {
        PayfastPaymentId = payfastPaymentId;
    }

    public String getPaymentStatus() {
        return PaymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        PaymentStatus = paymentStatus;
    }
    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String itemName) {
        ItemName = itemName;
    }

    public String getItemDescription() {
        return ItemDescription;
    }

    public void setItemDescription(String itemDescription) {
        ItemDescription = itemDescription;
    }

    public String getAmountGross() {
        return AmountGross;
    }

    public void setAmountGross(String amountGross) {
        AmountGross = amountGross;
    }

    public String getAmountFee() {
        return AmountFee;
    }

    public void setAmountFee(String amountFee) {
        AmountFee = amountFee;
    }

    public String getAmountNet() {
        return AmountNet;
    }

    public void setAmountNet(String amountNet) {
        AmountNet = amountNet;
    }

    public String getCustomStr1() {
        return CustomStr1;
    }

    public void setCustomStr1(String customStr1) {
        CustomStr1 = customStr1;
    }

    public String getCustomStr2() {
        return CustomStr2;
    }

    public void setCustomStr2(String customStr2) {
        CustomStr2 = customStr2;
    }
    public String getCustomStr3() {
        return CustomStr3;
    }

    public void setCustomStr3(String customStr3) {
        CustomStr3 = customStr3;
    }

    public String getCustomStr4() {
        return CustomStr4;
    }

    public void setCustomStr4(String customStr4) {
        CustomStr4 = customStr4;
    }

    public String getCustomStr5() {
        return CustomStr5;
    }

    public void setCustomStr5(String customStr5) {
        CustomStr5 = customStr5;
    }

    public int getCustomInt1() {
        return CustomInt1;
    }

    public void setCustomInt1(int customInt1) {
        CustomInt1 = customInt1;
    }

    public int getCustomInt2() {
        return CustomInt2;
    }

    public void setCustomInt2(int customInt2) {
        CustomInt2 = customInt2;
    }

    public int getCustomInt3() {
        return CustomInt3;
    }

    public void setCustomInt3(int customInt3) {
        CustomInt3 = customInt3;
    }

    public int getCustomInt4() {
        return CustomInt4;
    }

    public void setCustomInt4(int customInt4) {
        CustomInt4 = customInt4;
    }

    public int getCustomInt5() {
        return CustomInt5;
    }

    public void setCustomInt5(int customInt5) {
        CustomInt5 = customInt5;
    }

    public String getNameFirst() {
        return NameFirst;
    }

    public void setNameFirst(String nameFirst) {
        NameFirst = nameFirst;
    }

    public String getNameLast() {
        return NameLast;
    }

    public void setNameLast(String nameLast) {
        NameLast = nameLast;
    }

    public String getEmailAddress() {
        return EmailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        EmailAddress = emailAddress;
    }

    public int getMerchantId() {
        return MerchantId;
    }

    public void setMerchantId(int merchantId) {
        MerchantId = merchantId;
    }


    public String getSignature() {
        return Signature;
    }

    public void setSignature(String signature) {
        Signature = signature;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }


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
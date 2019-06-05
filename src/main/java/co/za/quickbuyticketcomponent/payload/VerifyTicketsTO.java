package co.za.quickbuyticketcomponent.payload;

import java.io.Serializable;


public class VerifyTicketsTO implements Serializable{


    public VerifyTicketsTO() {
    }

    @Override
    public String toString() {
        return "VerifyTicketsTO{" +
                "referenceNumber='" + referenceNumber + '\'' +
                ", status='" + sts + '\'' +
                '}';
    }

    public VerifyTicketsTO(String sts) {
        this.sts = sts;
    }

    private String referenceNumber;
    private String sts;

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public String getSts() {
        return sts;
    }

    public void setSts(String sts) {
        this.sts = sts;
    }
}

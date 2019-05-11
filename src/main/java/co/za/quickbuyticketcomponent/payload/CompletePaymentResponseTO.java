package co.za.quickbuyticketcomponent.payload;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class CompletePaymentResponseTO implements Serializable{

    @JsonProperty("qrcode")
    private String qrCodeRef;

    @JsonProperty("pdfref")
    private String pdfReference;


    @Override
    public String toString() {
        return "CompletePaymentResponseTO{" +
                "qrCodeRef='" + qrCodeRef + '\'' +
                ", pdfReference='" + pdfReference + '\'' +
                '}';
    }


    public String getQrCodeRef() {
        return qrCodeRef;
    }

    public void setQrCodeRef(String qrCodeRef) {
        this.qrCodeRef = qrCodeRef;
    }

    public String getPdfReference() {
        return pdfReference;
    }

    public void setPdfReference(String pdfReference) {
        this.pdfReference = pdfReference;
    }
}

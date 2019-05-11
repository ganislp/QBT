package co.za.quickbuyticketcomponent.payload;

public class NotificationSMSRequest {

    String cellNumber;
    String template;


    public String getCellNumber() {
        return cellNumber;
    }

    public void setCellNumber(String cellNumber) {
        this.cellNumber = cellNumber;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }
}

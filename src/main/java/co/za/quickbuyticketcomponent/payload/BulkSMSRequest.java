package co.za.quickbuyticketcomponent.payload;


import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "from",
        "to",
        "routingGroup",
        "encoding",
        "longMessageMaxParts",
        "body",
        "userSuppliedId",
        "protocolId",
        "messageClass",
        "deliveryReports"
})

public class BulkSMSRequest {

    @JsonProperty("from")
    private SmsFromAddress from;
    @JsonProperty("to")
    private List<ToAddressDetail> to = null;
    @JsonProperty("routingGroup")
    private String routingGroup;
    @JsonProperty("encoding")
    private String encoding;
    @JsonProperty("longMessageMaxParts")
    private Integer longMessageMaxParts;
    @JsonProperty("body")
    private String body;
    @JsonProperty("userSuppliedId")
    private String userSuppliedId;
    @JsonProperty("protocolId")
    private String protocolId;
    @JsonProperty("messageClass")
    private String messageClass;
    @JsonProperty("deliveryReports")
    private String deliveryReports;

    @JsonProperty("from")
    public SmsFromAddress getFrom() {
        return from;
    }

    @JsonProperty("from")
    public void setFrom(SmsFromAddress from) {
        this.from = from;
    }

    @JsonProperty("to")
    public List<ToAddressDetail> getTo() {
        return to;
    }

    @JsonProperty("to")
    public void setTo(List<ToAddressDetail> to) {
        this.to = to;
    }

    @JsonProperty("routingGroup")
    public String getRoutingGroup() {
        return routingGroup;
    }

    @JsonProperty("routingGroup")
    public void setRoutingGroup(String routingGroup) {
        this.routingGroup = routingGroup;
    }

    @JsonProperty("encoding")
    public String getEncoding() {
        return encoding;
    }

    @JsonProperty("encoding")
    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    @JsonProperty("longMessageMaxParts")
    public Integer getLongMessageMaxParts() {
        return longMessageMaxParts;
    }

    @JsonProperty("longMessageMaxParts")
    public void setLongMessageMaxParts(Integer longMessageMaxParts) {
        this.longMessageMaxParts = longMessageMaxParts;
    }

    @JsonProperty("body")
    public String getBody() {
        return body;
    }

    @JsonProperty("body")
    public void setBody(String body) {
        this.body = body;
    }

    @JsonProperty("userSuppliedId")
    public String getUserSuppliedId() {
        return userSuppliedId;
    }

    @JsonProperty("userSuppliedId")
    public void setUserSuppliedId(String userSuppliedId) {
        this.userSuppliedId = userSuppliedId;
    }

    @JsonProperty("protocolId")
    public String getProtocolId() {
        return protocolId;
    }

    @JsonProperty("protocolId")
    public void setProtocolId(String protocolId) {
        this.protocolId = protocolId;
    }

    @JsonProperty("messageClass")
    public String getMessageClass() {
        return messageClass;
    }

    @JsonProperty("messageClass")
    public void setMessageClass(String messageClass) {
        this.messageClass = messageClass;
    }

    @JsonProperty("deliveryReports")
    public String getDeliveryReports() {
        return deliveryReports;
    }

    @JsonProperty("deliveryReports")
    public void setDeliveryReports(String deliveryReports) {
        this.deliveryReports = deliveryReports;
    }

}


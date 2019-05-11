package co.za.quickbuyticketcomponent.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "type",
        "from",
        "to",
        "body",
        "encoding",
        "protocolId",
        "messageClass",
        "submission",
        "status",
        "relatedSentMessageId",
        "userSuppliedId",
        "numberOfParts",
        "creditCost"
})
public class BulkSMSResponse {

    @JsonProperty("id")
    private String id;
    @JsonProperty("type")
    private String type;
    @JsonProperty("from")
    private String from;
    @JsonProperty("to")
    private String to;
    @JsonProperty("body")
    private String body;
    @JsonProperty("encoding")
    private String encoding;
    @JsonProperty("protocolId")
    private Integer protocolId;
    @JsonProperty("messageClass")
    private Integer messageClass;
    @JsonProperty("submission")
    private Submission submission;
    @JsonProperty("status")
    private Status status;
    @JsonProperty("relatedSentMessageId")
    private String relatedSentMessageId;
    @JsonProperty("userSuppliedId")
    private String userSuppliedId;
    @JsonProperty("numberOfParts")
    private String numberOfParts;
    @JsonProperty("creditCost")
    private String creditCost;


    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("from")
    public String getFrom() {
        return from;
    }

    @JsonProperty("from")
    public void setFrom(String from) {
        this.from = from;
    }

    @JsonProperty("to")
    public String getTo() {
        return to;
    }

    @JsonProperty("to")
    public void setTo(String to) {
        this.to = to;
    }

    @JsonProperty("body")
    public String getBody() {
        return body;
    }

    @JsonProperty("body")
    public void setBody(String body) {
        this.body = body;
    }

    @JsonProperty("encoding")
    public String getEncoding() {
        return encoding;
    }

    @JsonProperty("encoding")
    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    @JsonProperty("protocolId")
    public Integer getProtocolId() {
        return protocolId;
    }

    @JsonProperty("protocolId")
    public void setProtocolId(Integer protocolId) {
        this.protocolId = protocolId;
    }

    @JsonProperty("messageClass")
    public Integer getMessageClass() {
        return messageClass;
    }

    @JsonProperty("messageClass")
    public void setMessageClass(Integer messageClass) {
        this.messageClass = messageClass;
    }

    @JsonProperty("submission")
    public Submission getSubmission() {
        return submission;
    }

    @JsonProperty("submission")
    public void setSubmission(Submission submission) {
        this.submission = submission;
    }

    @JsonProperty("status")
    public Status getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(Status status) {
        this.status = status;
    }

    @JsonProperty("relatedSentMessageId")
    public String getRelatedSentMessageId() {
        return relatedSentMessageId;
    }

    @JsonProperty("relatedSentMessageId")
    public void setRelatedSentMessageId(String relatedSentMessageId) {
        this.relatedSentMessageId = relatedSentMessageId;
    }

    @JsonProperty("userSuppliedId")
    public String getUserSuppliedId() {
        return userSuppliedId;
    }

    @JsonProperty("userSuppliedId")
    public void setUserSuppliedId(String userSuppliedId) {
        this.userSuppliedId = userSuppliedId;
    }

    @JsonProperty("numberOfParts")
    public String getNumberOfParts() {
        return numberOfParts;
    }

    @JsonProperty("numberOfParts")
    public void setNumberOfParts(String numberOfParts) {
        this.numberOfParts = numberOfParts;
    }

    @JsonProperty("creditCost")
    public String getCreditCost() {
        return creditCost;
    }

    @JsonProperty("creditCost")
    public void setCreditCost(String creditCost) {
        this.creditCost = creditCost;
    }

}

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "type",
        "subtype"
})
 class Status {

    @JsonProperty("id")
    private String id;
    @JsonProperty("type")
    private String type;
    @JsonProperty("subtype")
    private String subtype;

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("subtype")
    public String getSubtype() {
        return subtype;
    }

    @JsonProperty("subtype")
    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

}

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "date"
})
 class Submission {

    @JsonProperty("id")
    private String id;
    @JsonProperty("date")
    private String date;

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("date")
    public String getDate() {
        return date;
    }

    @JsonProperty("date")
    public void setDate(String date) {
        this.date = date;
    }


}

package co.za.quickbuyticketcomponent.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

public class ToAddressDetail {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
            "type",
            "address",
            "fields"
    })

        @JsonProperty("type")
        private String type;
        @JsonProperty("address")
        private String address;
        @JsonProperty("fields")
        private List<String> fields = null;

        public ToAddressDetail(String type, String address, List<String> fields) {
            this.type = type;
            this.address = address;
            this.fields = fields;
        }

    public ToAddressDetail() {
    }

    @JsonProperty("type")
        public String getType() {
            return type;
        }

        @JsonProperty("type")
        public void setType(String type) {
            this.type = type;
        }

        @JsonProperty("address")
        public String getAddress() {
            return address;
        }

        @JsonProperty("address")
        public void setAddress(String address) {
            this.address = address;
        }

        @JsonProperty("fields")
        public List<String> getFields() {
            return fields;
        }

        @JsonProperty("fields")
        public void setFields(List<String> fields) {
            this.fields = fields;
        }

    }

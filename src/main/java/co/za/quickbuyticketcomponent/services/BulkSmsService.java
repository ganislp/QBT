package co.za.quickbuyticketcomponent.services;


import co.za.quickbuyticketcomponent.payload.BulkSMSRequest;
import co.za.quickbuyticketcomponent.payload.BulkSMSResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;

@Component
public class BulkSmsService {


    public void sendReferenceNotification(BulkSMSRequest notificationSMSRequest) {
        RestTemplate restTemplate = new RestTemplate();
        BulkSMSRequest[] notificationSMSRequests = new BulkSMSRequest[]{notificationSMSRequest};
        ObjectMapper Obj = new ObjectMapper();
        HttpEntity<?> request = new HttpEntity<Object>(notificationSMSRequests, createHeaders("vinayvadlamuri", "vinaymvinnu"));
        try {
            String jsonStr = Obj.writeValueAsString(notificationSMSRequests);
            System.out.println("jsonStr :" + jsonStr);

            BulkSMSResponse[] bulkSMSResponse = restTemplate.postForObject("https://api.bulksms.com/v1/messages", request, BulkSMSResponse[].class);
            System.out.println("bulkSMSResponse :" + bulkSMSResponse[0]);
        } catch (Exception e) {
            BulkSMSResponse[] bulkSMSResponse = restTemplate.postForObject("https://api.bulksms.com/v1/messages", request, BulkSMSResponse[].class);
            System.out.println("bulkSMSResponse :" + bulkSMSResponse[0]);
            e.printStackTrace();
        }
    }

    HttpHeaders createHeaders(String username, String password) {
        return new HttpHeaders() {{
            String auth = username + ":" + password;
            byte[] encodedAuth = Base64.encodeBase64(
                    auth.getBytes(Charset.forName("US-ASCII")));
            String authHeader = "Basic " + new String(encodedAuth);
            set("Authorization", authHeader);
        }};
    }

}

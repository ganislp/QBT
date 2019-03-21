package co.za.quickbuyticketcomponent.modals;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by gVadlamuri on 2/2/2018.
 */
@Configuration
@PropertySource("classpath:application.properties")
public class ApplicationProperties {

    @Value("${security.auth.token.expire.time}")
    private int expireTime;

    @Value("${security.auth.token.api.key}")
    private String apiKey;

    public int getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(int expireTime) {
        this.expireTime = expireTime;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

}
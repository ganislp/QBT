package co.za.quickbuyticketcomponent.utils;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.ResourceBundle;
@Component
public class ResourceUtil implements MessageSourceAware {
    /** The message source. */
    private static MessageSource messageSource;

    /**
     * Instantiates a new resource util.
     */
    public ResourceUtil(){

    }
    /**
     * Sets the message source.
     *
     * @param arg the Message Source
     *
     *
     */
    public void setMessageSource(MessageSource arg) {

        messageSource = arg;
    }

    /**
     * Gets the message.
     *
     * @param errorCode the error code
     *
     * @return the message
     */
    public static String getMessage(Long errorCode) {
        Locale.setDefault(Locale.US);
        return messageSource.getMessage(String.valueOf(errorCode), null, Locale.getDefault());
    }

    /**
     * Gets the message.
     *
     * @param errorCode the error code
     * @param args the args
     *
     * @return the message
     */
    public static String getMessage(Long errorCode, Object[] args) {
        Locale.setDefault(Locale.US);
        return messageSource.getMessage(String.valueOf(errorCode), args, Locale.getDefault());
    }

    /**
     * Gets the message.
     *
     * @param key The key for getting message
     *
     * @return the message
     */
    public static String getMessage(String key) {
        Locale.setDefault(Locale.US);
        return messageSource.getMessage(key, null , Locale.getDefault());
    }

    /**
     * This method is used to get the message based on the given key from environment.properties
     * properties file.
     *
     * @param key
     *            Resource bundle key
     * @return String Returns message for the given key
     */
   /* public static String getEnvironmentMessage(String key) {

        return ResourceBundle.getBundle(EventManagementSystemConstant.ENVIRONMENT_PATH).getString(key);
    }*/
}

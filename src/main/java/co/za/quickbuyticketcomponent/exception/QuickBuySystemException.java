package co.za.quickbuyticketcomponent.exception;

import java.io.Serializable;

public class QuickBuySystemException extends QuickBuyException implements Serializable {
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 8687136332380569279L;


    /**
     * Instantiates a new event management system exception.
     *
     * @param message the message
     */
    public QuickBuySystemException(String message){
        super(message);
    }

    /**
     * Instantiates a new event management system exception.
     *
     * @param errorCode the error code
     * @param errorMessage the error message
     */
    public QuickBuySystemException(Long errorCode, String errorMessage) {
        super(errorCode,errorMessage);
    }

    /**
     * Instantiates a new event management system exception.
     *
     * @param errorCode the error code
     * @param errorMessage the error message
     * @param rootException the root exception
     */
    public QuickBuySystemException(Long errorCode, String errorMessage, Throwable rootException) {

        super(errorCode, errorMessage, rootException);
    }

    /**
     * Instantiates a new event management system exception.
     *
     * @param message the message
     * @param rootException the root exception
     */
    public QuickBuySystemException(String message,Throwable rootException){
        super(message,rootException);
    }
}

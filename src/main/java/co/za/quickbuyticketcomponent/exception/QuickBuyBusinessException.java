package co.za.quickbuyticketcomponent.exception;

import java.io.Serializable;

public class QuickBuyBusinessException extends  QuickBuyException implements Serializable {
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -7660744500401092901L;

    /**
     * Instantiates a new event management business exception.
     *
     * @param message the message
     */
    public QuickBuyBusinessException(String message) {
        super(message);
    }

    /**
     * Instantiates a new event management business exception.
     *
     * @param errorCode the error code
     * @param message the message
     */
    public QuickBuyBusinessException(Long errorCode, String message) {
        super(errorCode, message);
    }

    /**
     * Instantiates a new event management business exception.
     *
     * @param message the message
     * @param cause the cause
     */
    public QuickBuyBusinessException(String message, Throwable cause) {

        super(message, cause);
    }

    /**
     * Instantiates a new event management business exception.
     *
     * @param errorCode the error code
     * @param message the message
     * @param exception the exception
     */
    public QuickBuyBusinessException(Long errorCode, String message, Throwable exception) {

        super(errorCode, message, exception);
    }



}

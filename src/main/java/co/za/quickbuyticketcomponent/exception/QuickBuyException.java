package co.za.quickbuyticketcomponent.exception;

import org.springframework.core.NestedCheckedException;

public class QuickBuyException extends NestedCheckedException {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -6081560975864066476L;

    /** The error code. */
    private Long errorCode;

    /**
     * Instantiates a new event management business exception.
     *
     * @param message the message
     */
    public QuickBuyException(String message) {
        super(message);
    }

    /**
     * Instantiates a new event management business exception.
     *
     * @param message the message
     * @param throwable the throwable
     */
    public QuickBuyException(String message, Throwable throwable) {
        super(message, throwable);
    }

    /**
     * Instantiates a new event management business exception.
     *
     * @param errorCode the error code
     * @param message the message
     */
    public QuickBuyException(Long errorCode,String message) {
        this(message);
        this.errorCode = errorCode;
    }

    /**
     * Instantiates a new event management business exception.
     *
     * @param errorCode the error code
     * @param message the message
     * @param exception the exception
     */
    public QuickBuyException(Long errorCode, String message, Throwable exception) {
        this(message, exception);
        this.errorCode = errorCode;
    }

    /**
     * Gets the error code.
     *
     * @return the error code
     */
    public Long getErrorCode() {
        return errorCode;
    }

    /**
     * Sets the error code.
     *
     * @param errorCode the new error code
     */
    public void setErrorCode(Long errorCode) {
        this.errorCode = errorCode;
    }
}

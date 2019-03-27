package co.za.quickbuyticketcomponent.utils;

import co.za.quickbuyticketcomponent.exception.QuickBuyBusinessException;
import co.za.quickbuyticketcomponent.exception.QuickBuySystemException;
import org.springframework.stereotype.Component;

import static co.za.quickbuyticketcomponent.utils.ResourceUtil.getMessage;
import java.math.BigInteger;
import java.util.Random;

@Component
public class QuickBuyUtil {
    /**
     * Instantiates a new event management util.
     */
    private QuickBuyUtil(){

    }
    /**
     * Generates Unique Reference Number for Create Event Request.
     *
     * @return the unique reference number string
     */
  /*  public static String generateReferenceNumber() {
        Random random = new Random();
        String string = ResourceUtil.getEnvironmentMessage(EventManagementSystemConstant.END_REFERENCE_NUMBER);
        StringBuilder innerCode = new StringBuilder();

        for (int y = 0; y <= string.length(); y++) {
            innerCode.append(string.charAt(random.nextInt(string.length())));
        }

        return String.valueOf(innerCode);
    }*/

    /**
     * Returns Big Integer from input String.
     *
     * @param bigIntegerString the string for big integer
     *
     * @return the big integer
     */
    public static BigInteger getBigInteger(String bigIntegerString) {
        return new BigInteger(bigIntegerString);
    }

    /**
     * Returns Big Integer from input integer value.
     *
     * @param value the value
     *
     * @return the big integer
     */
    public static BigInteger getBigIntegerValue(Object value) {
        return new BigInteger(String.valueOf(value));
    }


    /**
     * Returns Long from input Big Integer.
     *
     * @param bigInteger the big integer for long
     *
     * @return the long
     */
    public static Long getLong(BigInteger bigInteger) {
        return bigInteger.longValue();
    }

    /**
     * Throw Event Management system exception.
     *
     * @param errorCode the error code
     * @param exception the exception
     *
     * @throws QuickBuySystemException the event management system exception
     */
    public static void throwQuickBuySystemException(long errorCode, Throwable exception)
            throws QuickBuySystemException {
        throw new QuickBuySystemException(errorCode, getMessage(errorCode), exception);
    }

    /**
     * Throw Event Management system exception.
     *
     * @param errorCode the error code
     *
     * @throws QuickBuySystemException the event management system exception
     */
    public static void throwQuickBuySystemException(long errorCode)
            throws QuickBuySystemException {
        throw new QuickBuySystemException(errorCode, getMessage(errorCode));
    }

    /**
     * Throw event management business exception.
     *
     * @param errorCode the error code
     *
     * @throws QuickBuyBusinessException the event management business exception
     */


    public static void throwQuickBuyBusinessException(long errorCode)
            throws QuickBuyBusinessException {
        throw new QuickBuyBusinessException(errorCode, getMessage(errorCode));
    }

    public static void throwQuickBuyBusinessException(String errorCode)
            throws QuickBuyBusinessException {
        throw new QuickBuyBusinessException(errorCode);
    }

    /**
     * Checks if object is null .
     *
     * @param object the object
     *
     * @return true, if is empty
     */
    public static boolean isEmpty(Object object) {
        return (null == object);
    }

    /** Change for SERP 171071 Starts */

    /**
     * Checks if object is not null .
     *
     * @param object the object
     *
     * @return true, if is empty
     */
    public static boolean isNotEmpty(Object object) {
        return (null != object);
    }

    /** Change for SERP 171071 End */

    /**
     * Throw event management business exception.
     *
     *
     * @throws QuickBuyBusinessException the event management business exception
     */
    public  void showSuccessMessage(long successCode)
            throws QuickBuyBusinessException {
        throw new QuickBuyBusinessException(successCode, getMessage(successCode));
    }

    public static String convert(String str)
    {
        // Create a char array of given String
        char ch[] = str.toCharArray();
        for (int i = 0; i < str.length(); i++) {
            // If first character of a word is found
            if (i == 0 && ch[i] != ' ' ||
                    ch[i] != ' ' && ch[i - 1] == ' ') {
                // If it is in lower-case
                if (ch[i] >= 'a' && ch[i] <= 'z') {
                    // Convert into Upper-case
                    ch[i] = (char)(ch[i] - 'a' + 'A');
                }
            }
            // If apart from first character
            // Any one is in Upper-case
            else if (ch[i] >= 'A' && ch[i] <= 'Z')
                // Convert into Lower-Case
                ch[i] = (char)(ch[i] + 'a' - 'A');
        }
        // Convert the char array to equivalent String
        String st = new String(ch);
        return st;
    }
}

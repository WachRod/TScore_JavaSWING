package tscore;

/* IntegrationException.java
 * Project: Math Tools in Java
 * Purpose : Exception for Integration.
 * Author:  Wachara R.
 * First Released: Wed 07 Mar 2007
 * Last Updated : Sun 11 Mar 2007
 */
/** Exception for Integration */

public class IntegrationException extends Exception {
    public static final String INVALID_LIMITS = "UPPER LIMIT EQUALS LOWER LIMIT.";
    public static final String INVALID_INTERVALS = "INTERVAL MUST GREATER THAN ZERO.";
    public static final String INVALID_NODE = "NODE VALUE MUST BE BETWEEN 2 to 8";

    /**
     * constructor.
     * @param err the error message
     */
    public IntegrationException (String err ){
        super(err);
    }

}

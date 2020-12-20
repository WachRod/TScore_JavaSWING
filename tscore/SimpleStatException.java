package tscore;
    /*file : SimpleStatException.java
     *  Math Tools in Java
     * by Wachara R.
     * First Released: Mon 07 May 2007
     * Last Updated :
     */
    public class SimpleStatException extends Exception {
        public static final String NO_MODE		= "THIS BUNDLE OF DATA HAS NO MODE";
        public static final String BAD_AREA = " AREA UNDER NORMAL CURVE MUST BE BETWEEN 0 to 1.";

        /**
         * constructor.
         * @param err the error message
         */
        public SimpleStatException (String err ){
            super(err);
        }

    }
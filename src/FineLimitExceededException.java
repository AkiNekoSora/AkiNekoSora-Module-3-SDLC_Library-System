/*
 * Autumn Skye
 * CEN-3024C 13950
 * September 3rd, 2025
 *
 * Exception class used when a fine exceeds $250
 */

public class FineLimitExceededException extends Exception {
    /* Method Name: Fine Limit Exceeded Exception
     * Purpose: Used to return to the CMI when a fine exceeds $250
     * Parameters: A string message from the Patrons Class
     */
    public FineLimitExceededException(String message) {
        super(message);
    }
}

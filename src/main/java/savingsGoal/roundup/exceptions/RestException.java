package savingsGoal.roundup.exceptions;

/**
 * This is the customer Rest Exception class.
 */
public class RestException extends RuntimeException{
    public RestException(String msg){
        super(msg);
    }
}

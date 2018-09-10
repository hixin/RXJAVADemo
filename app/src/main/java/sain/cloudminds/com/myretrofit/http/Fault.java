package sain.cloudminds.com.myretrofit.http;

/**
 * created by sain on 9/10/18
 */
public class Fault extends RuntimeException{
    private int errorCode;

    public Fault(int errorCode, String message) {
        super(message);
        errorCode = errorCode;
    }
    public int getErrorCode() {
        return errorCode;
    }
}

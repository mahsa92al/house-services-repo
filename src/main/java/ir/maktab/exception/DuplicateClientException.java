package ir.maktab.exception;

/**
 * @author Mahsa Alikhani m-58
 */
public class DuplicateClientException extends RuntimeException{

    public DuplicateClientException(String message) {
        super(message);
    }
}

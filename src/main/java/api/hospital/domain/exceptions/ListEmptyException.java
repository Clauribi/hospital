package api.hospital.domain.exceptions;

public class ListEmptyException extends Exception{
    public ListEmptyException(String message) {
        super(message);
    }
}

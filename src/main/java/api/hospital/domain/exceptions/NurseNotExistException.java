package api.hospital.domain.exceptions;

public class NurseNotExistException extends Exception{
    public NurseNotExistException(String message) {
        super(message);
    }
}

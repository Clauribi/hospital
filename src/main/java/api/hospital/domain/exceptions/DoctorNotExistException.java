package api.hospital.domain.exceptions;

public class DoctorNotExistException extends Exception{
    public DoctorNotExistException(String message) {
        super(message);
    }
}

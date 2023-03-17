package api.hospital.domain.exceptions;

public class DoctorExistException extends Exception{
    public DoctorExistException(String message) {
        super(message);
    }
}

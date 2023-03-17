package api.hospital.domain.exceptions;

public class PatientNotExistException extends Exception{
    public PatientNotExistException(String message) {
        super(message);
    }
}

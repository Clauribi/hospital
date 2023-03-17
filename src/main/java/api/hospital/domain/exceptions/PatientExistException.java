package api.hospital.domain.exceptions;

public class PatientExistException extends Exception {
    public PatientExistException(String message) {
        super(message);
    }
}

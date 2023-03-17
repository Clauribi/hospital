package api.hospital.domain.exceptions;

public class AppointmentPatientExistException extends Exception{
    public AppointmentPatientExistException(String message) {
        super(message);
    }
}

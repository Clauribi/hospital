package api.hospital.domain.exceptions;

public class AppointmentExistException extends Exception{
    public AppointmentExistException(String message) {
        super(message);
    }
}

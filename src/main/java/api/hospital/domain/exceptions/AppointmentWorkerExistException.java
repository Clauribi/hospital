package api.hospital.domain.exceptions;

public class AppointmentWorkerExistException extends Exception{
    public AppointmentWorkerExistException(String message) {
        super(message);
    }
}

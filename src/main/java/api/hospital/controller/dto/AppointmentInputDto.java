package api.hospital.controller.dto;

import api.hospital.domain.Appointment;
import api.hospital.domain.exceptions.OutOfTimeWindow;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;


public class AppointmentInputDto {
    @NotNull(message = "Id is null.")
    @NotBlank(message = "Id is empty.")
    private String id;
    @NotNull(message = "Day is null.")
    private LocalDate appointmentDay;
    @NotNull(message = "Time is null.")
    private LocalTime appointmentTime;
    @NotNull(message = "Patient id is null.")
    @NotBlank(message = "Patient id is empty.")
    private String patientId;
    @NotNull(message = "Worker id is null.")
    @NotBlank(message = "Worker id is empty.")
    private String workerId;

    public AppointmentInputDto() {
    }

    public AppointmentInputDto(String id, LocalDate appointmentDay, LocalTime appointmentTime, String patientId, String workerId) throws OutOfTimeWindow {
        this.id = id;
        this.appointmentDay = appointmentDay;
        this.appointmentTime = appointmentTime;
        this.patientId = patientId;
        this.workerId = workerId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getAppointmentDay() {
        return appointmentDay;
    }

    public void setAppointmentDay(LocalDate appointmentDay) {
        this.appointmentDay = appointmentDay;
    }

    public LocalTime getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(LocalTime appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getWorkerId() {
        return workerId;
    }

    public void setWorkerId(String workerId) {
        this.workerId = workerId;
    }

    public Appointment toDomain() {
        return new Appointment(this.id, this.appointmentDay, this.appointmentTime, this.patientId, this.workerId);
    }

}

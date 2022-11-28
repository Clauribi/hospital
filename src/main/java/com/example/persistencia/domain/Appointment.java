package com.example.persistencia.domain;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;


@Entity(name = "appointments")

public class Appointment {
    @Id
    private String id;
    @NotNull(message = "Day is null.")
    private LocalDate appointmentDay;
    @NotNull(message = "Time is null.")
    private LocalTime appointmentTime;
    @NotNull(message = "Patient id is null.")
    @NotBlank(message = "Patient id is empty.")
    private String patientId;
    private String workerId;


    public Appointment() {
    }

    public Appointment(String id, LocalDate appointmentDay, LocalTime appointmentTime, String patientId, String workerId) {
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

}

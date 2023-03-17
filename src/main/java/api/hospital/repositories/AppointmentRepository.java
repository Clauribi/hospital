package api.hospital.repositories;

import api.hospital.domain.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, String> {

    List<Appointment> findByPatientIdAndAppointmentDayOrderByAppointmentTime(String id, LocalDate day);

    boolean existsByAppointmentDayAndAppointmentTimeAndPatientId(LocalDate appointmentDay, LocalTime appointmentTime, String patientId);

    boolean existsByAppointmentDayAndAppointmentTimeAndWorkerId(LocalDate appointmentDay, LocalTime appointmentTime, String workerId);

    ArrayList<Appointment> findByAppointmentDayAndWorkerId(LocalDate nextDay, String id);
}

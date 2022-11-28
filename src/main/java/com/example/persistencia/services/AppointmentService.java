package com.example.persistencia.services;

import com.example.persistencia.controller.dto.AppointmentInputDto;
import com.example.persistencia.domain.Appointment;
import com.example.persistencia.domain.exceptions.*;
import com.example.persistencia.repositories.AppointmentRepository;
import com.example.persistencia.repositories.DoctorRepository;
import com.example.persistencia.repositories.NurseRepository;
import com.example.persistencia.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class AppointmentService {
    @Autowired
    AppointmentRepository appointmentRepository;
    @Autowired
    PatientRepository patientRepository;
    @Autowired
    NurseRepository nurseRepository;
    @Autowired
    NurseService nurseService;
    @Autowired
    DoctorRepository doctorRepository;


    public void createAppointment(AppointmentInputDto appointmentInputDto) throws PatientNotExistException, NurseNotExistException, AppointmentExistException, AppointmentPatientExistException, AppointmentWorkerExistException, OutOfTimeWindow, DoctorNotExistException {
        if (!patientRepository.existsById(appointmentInputDto.getPatientId()))
            throw new PatientNotExistException("Patient doesn´t exist.");
        if (!nurseRepository.existsById(appointmentInputDto.getWorkerId()))
            throw new NurseNotExistException("Nurse doesn´t exist.");
        if (!doctorRepository.existsById(appointmentInputDto.getWorkerId()))
            throw new DoctorNotExistException("Doctor doesn´t exist.");
        if (appointmentRepository.existsById(appointmentInputDto.getId()))
            throw new AppointmentExistException("Appointment already exist.");
        if (appointmentRepository.existsByAppointmentDayAndAppointmentTimeAndPatientId(appointmentInputDto.getAppointmentDay(), appointmentInputDto.getAppointmentTime(), appointmentInputDto.getPatientId()))
            throw new AppointmentPatientExistException("This patient already has an appointment for these day and time.");
        if (appointmentRepository.existsByAppointmentDayAndAppointmentTimeAndWorkerId(appointmentInputDto.getAppointmentDay(), appointmentInputDto.getAppointmentTime(), appointmentInputDto.getWorkerId()))
            throw new AppointmentWorkerExistException("This worker already has an appointment for these day and time.");
        if (!nurseService.getTimeWindow(LocalDate.now()).contains(appointmentInputDto.getAppointmentDay()))
            throw new OutOfTimeWindow("This appointment day is out of time window.");
        Appointment appointment = appointmentInputDto.toDomain();
        appointmentRepository.save(appointment);
    }

    public List<Appointment> listAppointmentsByIdAndDay(String id, LocalDate day) throws PatientNotExistException, ListEmptyException {
        if (!patientRepository.existsById(id)) throw new PatientNotExistException("Patient doesn´t exist.");
        List<Appointment> listAppointmentsByIdAndDay = appointmentRepository.findByPatientIdAndAppointmentDayOrderByAppointmentTime(id, day);
        if (listAppointmentsByIdAndDay.isEmpty())
            throw new ListEmptyException("List of appointments for the patient " + id + "is empty.");
        return listAppointmentsByIdAndDay;
    }


}

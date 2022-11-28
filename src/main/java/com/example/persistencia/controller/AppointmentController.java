package com.example.persistencia.controller;

import com.example.persistencia.controller.dto.AppointmentInputDto;
import com.example.persistencia.domain.Appointment;
import com.example.persistencia.domain.exceptions.*;
import com.example.persistencia.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

import static org.springframework.format.annotation.DateTimeFormat.ISO.DATE;
import static org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME;

@RestController
public class AppointmentController {
    @Autowired
    AppointmentService appointmentService;

    @PostMapping("/appointments")
    ResponseEntity<AppointmentInputDto> createAppointment(@Valid @RequestBody @DateTimeFormat(iso = DATE_TIME) AppointmentInputDto appointmentInputDto){
        try{
            appointmentService.createAppointment(appointmentInputDto);
        }catch (PatientNotExistException e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }catch (NurseNotExistException e1) {
            e1.printStackTrace();
            return ResponseEntity.badRequest().build();
        }catch (DoctorNotExistException e2) {
            e2.printStackTrace();
            return ResponseEntity.badRequest().build();
        }catch (AppointmentExistException e3) {
            e3.printStackTrace();
            return ResponseEntity.badRequest().build();
        }catch (AppointmentPatientExistException e4) {
            e4.printStackTrace();
            return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).build();
        }catch (AppointmentWorkerExistException e5) {
            e5.printStackTrace();
            return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).build();
        }catch (OutOfTimeWindow e6) {
            e6.printStackTrace();
            return ResponseEntity.badRequest().build();
        }catch (Exception e7) {
            e7.printStackTrace();
            return ResponseEntity.notFound().build();
        }
            return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/appointments/patient/{id}/{day}")
    ResponseEntity<List<Appointment>> getListAppointmentsbyPatientIdAndDay(@PathVariable String id, @PathVariable @DateTimeFormat(iso = DATE) LocalDate day){
        try{
            List<Appointment> appointmentsByPatient= appointmentService.listAppointmentsByIdAndDay(id,day);
            return ResponseEntity.ok(appointmentsByPatient);

        } catch (PatientNotExistException e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }catch (ListEmptyException e1){
            e1.printStackTrace();
            return ResponseEntity.notFound().build();
        }catch (Exception e2){
            return ResponseEntity.notFound().build();
        }

    }
}

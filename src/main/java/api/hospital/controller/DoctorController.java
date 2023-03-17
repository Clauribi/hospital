package api.hospital.controller;

import api.hospital.domain.exceptions.DoctorNotExistException;
import api.hospital.domain.exceptions.TimeException;
import api.hospital.controller.dto.DoctorInputDto;
import api.hospital.domain.exceptions.DoctorExistException;
import api.hospital.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static org.springframework.format.annotation.DateTimeFormat.ISO.DATE;

@RestController

public class DoctorController {
    @Autowired
    private DoctorService doctorService;

    @PostMapping("/doctors")
    public ResponseEntity<DoctorInputDto> createDoctor(@Valid @RequestBody DoctorInputDto doctorInputDto){
        try{
            doctorService.addDoctor(doctorInputDto);
        } catch (DoctorExistException e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        } catch (Exception e1){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @PostMapping("/doctors/{id}/schedule/{startTime}/{endTime}")
    public ResponseEntity<List<LocalTime>> createScheduleDoctor(@PathVariable String id, @PathVariable LocalTime startTime, @PathVariable LocalTime endTime){
        try{
            doctorService.addSchedule(id, startTime, endTime);
        } catch (DoctorNotExistException e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        } catch (TimeException e1){
            return ResponseEntity.badRequest().build();
        } catch (Exception e1) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @GetMapping("/doctors/{id}/schedule/{day}")
    public ResponseEntity<LinkedHashMap<LocalDate, ArrayList<LocalTime>>> getOpenings(@PathVariable String id, @PathVariable @DateTimeFormat(iso = DATE) LocalDate day) {
        try {
            LinkedHashMap<LocalDate, ArrayList<LocalTime>> listOpenings = doctorService.getOpenings(id, day);
            return ResponseEntity.ok(listOpenings);
        } catch (DoctorNotExistException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        } catch (Exception e1) {
            return ResponseEntity.notFound().build();
        }
    }
}

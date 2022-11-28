package com.example.persistencia.controller;

import com.example.persistencia.controller.dto.NurseInputDto;
import com.example.persistencia.domain.exceptions.NurseExistException;
import com.example.persistencia.domain.exceptions.NurseNotExistException;
import com.example.persistencia.domain.exceptions.TimeException;
import com.example.persistencia.services.NurseService;
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
public class NurseController {
    @Autowired
    private NurseService nurseService;

    @PostMapping("/nurses")
    public ResponseEntity<NurseInputDto> createNurse(@Valid @RequestBody NurseInputDto nurseInputDto){
        try{
            nurseService.addNurse(nurseInputDto);
        } catch (NurseExistException e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        } catch (Exception e1){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @PostMapping("/nurses/{id}/schedule/{startTime}/{endTime}")
    public ResponseEntity<List<LocalTime>> createScheduleNurse(@PathVariable String id, @PathVariable LocalTime startTime, @PathVariable LocalTime endTime){
        try{
            nurseService.addSchedule(id, startTime, endTime);
        } catch (NurseNotExistException e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        } catch (TimeException e1){
            return ResponseEntity.badRequest().build();
        } catch (Exception e1) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @GetMapping("/nurses/{id}/schedule/{day}")
    public ResponseEntity<LinkedHashMap<LocalDate, ArrayList<LocalTime>>> getOpenings(@PathVariable String id, @PathVariable @DateTimeFormat(iso = DATE) LocalDate day) {
        try {
            LinkedHashMap<LocalDate, ArrayList<LocalTime>> listOpenings = nurseService.getOpenings(id, day);
            return ResponseEntity.ok(listOpenings);
        } catch (NurseNotExistException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        } catch (Exception e1) {
            return ResponseEntity.notFound().build();
        }



    }


}

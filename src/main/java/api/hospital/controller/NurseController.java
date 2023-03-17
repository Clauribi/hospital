package api.hospital.controller;

import api.hospital.domain.exceptions.NurseExistException;
import api.hospital.domain.exceptions.NurseNotExistException;
import api.hospital.domain.exceptions.TimeException;
import api.hospital.controller.dto.NurseInputDto;
import api.hospital.services.NurseService;
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

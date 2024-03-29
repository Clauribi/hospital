package api.hospital.controller;

import api.hospital.controller.dto.PatientInputDto;
import api.hospital.domain.exceptions.PatientExistException;
import api.hospital.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class PatientController {
    @Autowired
    private PatientService patientService;

    @PostMapping("/patients")
    public ResponseEntity<PatientInputDto> createPatient(@Valid @RequestBody PatientInputDto patientInputDto){
        try{
            patientService.addPatient(patientInputDto);
        } catch (PatientExistException e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        } catch (Exception e1){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}

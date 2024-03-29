package api.hospital.services;

import api.hospital.controller.dto.PatientInputDto;
import api.hospital.domain.Patient;
import api.hospital.repositories.PatientRepository;
import api.hospital.domain.exceptions.PatientExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;

    public void addPatient(PatientInputDto patientInputDto) throws PatientExistException {
        if(patientRepository.existsById(patientInputDto.getId()))
            throw new PatientExistException("Patient already exists.");
        Patient patient = patientInputDto.toDomain();
        patientRepository.save(patient);
    }

}

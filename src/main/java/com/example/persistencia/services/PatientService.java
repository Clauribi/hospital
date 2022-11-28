package com.example.persistencia.services;

import com.example.persistencia.controller.dto.PatientInputDto;
import com.example.persistencia.domain.Patient;
import com.example.persistencia.domain.exceptions.PatientExistException;
import com.example.persistencia.repositories.PatientRepository;
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

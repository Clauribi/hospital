package com.example.persistencia.services;

import com.example.persistencia.controller.dto.NurseInputDto;
import com.example.persistencia.domain.Appointment;
import com.example.persistencia.domain.Nurse;
import com.example.persistencia.domain.exceptions.NurseExistException;
import com.example.persistencia.domain.exceptions.NurseNotExistException;
import com.example.persistencia.domain.exceptions.TimeException;
import com.example.persistencia.repositories.AppointmentRepository;
import com.example.persistencia.repositories.NurseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.*;


@Service
public class NurseService {
    @Autowired
    private NurseRepository nurseRepository;
    @Autowired
    private AppointmentRepository appointmentRepository;

    public void addNurse(NurseInputDto nurseInputDto) throws NurseExistException {
        if(nurseRepository.existsById(nurseInputDto.getId()))
            throw new NurseExistException("Nurse already exists.");
        Nurse nurse = nurseInputDto.toDomain();
        nurseRepository.save(nurse);
    }
    public void addSchedule(String id, LocalTime startTime, LocalTime endTime) throws TimeException, NurseNotExistException {
        if (!nurseRepository.existsById(id)) throw new NurseNotExistException("Nurse doesn´t exist.");
        Nurse nurse = nurseRepository.findById(id).get();
        nurse.configurateSchedule(startTime, endTime);
        nurseRepository.save(nurse);
    }

    //consultar huecos libres de un enfermero. getOpenings
    public LinkedHashMap<LocalDate, ArrayList<LocalTime>> getOpenings(String id, LocalDate day) throws NurseNotExistException, TimeException {
        if (!nurseRepository.existsById(id)) throw new NurseNotExistException("Nurse doesn´t exist.");
        Nurse nurse = nurseRepository.findById(id).get();
        LinkedHashMap<LocalDate, ArrayList<LocalTime>> listOpenings = new LinkedHashMap<>();
        List<LocalDate> nextWeek = getTimeWindow(day);
        ArrayList<LocalTime> hours = new ArrayList<>();
        hours.add(nurse.getStartTime());
        hours.add(nurse.getEndTime());
        ArrayList<LocalTime> hoursListInit = getHoursList(hours);
        ArrayList<LocalTime> hoursList;
        for (LocalDate nextDay : nextWeek) {
          ArrayList<Appointment>  appointmentList = appointmentRepository.findByAppointmentDayAndWorkerId(nextDay, id);
            hoursList = hoursListInit;
            for(Appointment appointment: appointmentList){
                    hoursList.remove(appointment.getAppointmentTime());
            listOpenings.put(nextDay, hoursList);
          }
        }
        return listOpenings;
    }

    private ArrayList<LocalTime> getHoursList(ArrayList<LocalTime> hours) {
        ArrayList<LocalTime> hoursList = new ArrayList<>();
        int hoursNumber= hours.get(1).getHour()-hours.get(0).getHour();
        for(int i =0; i<=hoursNumber;i++){
            LocalTime nextHour =hours.get(0).plusHours(i);
            hoursList.add(nextHour);
        }
        return hoursList;
    }

    public List<LocalDate> getTimeWindow(LocalDate day) {
        List<LocalDate> nextWeek = new ArrayList<>();
        LocalDate day1 = day.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        for (int i = 0; i <= 4; i++) {
            day = day1.plusDays(i);
            nextWeek.add(day);
        }
        return nextWeek;
    }


}

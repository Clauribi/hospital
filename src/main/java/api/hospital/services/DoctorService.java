package api.hospital.services;

import api.hospital.controller.dto.DoctorInputDto;
import api.hospital.domain.Appointment;
import api.hospital.domain.Doctor;
import api.hospital.domain.Nurse;
import api.hospital.domain.exceptions.DoctorNotExistException;
import api.hospital.domain.exceptions.TimeException;
import api.hospital.repositories.AppointmentRepository;
import api.hospital.repositories.DoctorRepository;
import api.hospital.domain.exceptions.DoctorExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Service
public class DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private AppointmentRepository appointmentRepository;

    public void addDoctor(DoctorInputDto doctorInputDto) throws DoctorExistException {
        if (doctorRepository.existsById(doctorInputDto.getId()))
            throw new DoctorExistException("Doctor already exists.");
        Doctor doctor = doctorInputDto.toDomain();
        doctorRepository.save(doctor);
    }

    public void addSchedule(String id, LocalTime startTime, LocalTime endTime) throws DoctorNotExistException, TimeException {
        if (!doctorRepository.existsById(id)) throw new DoctorNotExistException("Doctor doesn´t exist.");
        Doctor doctor = doctorRepository.findById(id).get();
        doctor.configurateSchedule(startTime, endTime);
        doctorRepository.save(doctor);
    }

    public LinkedHashMap<LocalDate, ArrayList<LocalTime>> getOpenings(String id, LocalDate day) throws DoctorNotExistException {
        if (!doctorRepository.existsById(id)) throw new DoctorNotExistException("Doctor doesn´t exist.");
        Nurse nurse = doctorRepository.findById(id).get();
        LinkedHashMap<LocalDate, ArrayList<LocalTime>> listOpenings = new LinkedHashMap<>();
        List<LocalDate> nextWeek = getTimeWindow(day);
        ArrayList<LocalTime> hours = new ArrayList<>();
        hours.add(nurse.getStartTime());
        hours.add(nurse.getEndTime());
        ArrayList<LocalTime> hoursListInit = getHoursList(hours);
        ArrayList<LocalTime> hoursList;
        for (LocalDate nextDay : nextWeek) {
            ArrayList<Appointment> appointmentList = appointmentRepository.findByAppointmentDayAndWorkerId(nextDay, id);
            hoursList = hoursListInit;
            for (Appointment appointment : appointmentList) {
                hoursList.remove(appointment.getAppointmentTime());
                listOpenings.put(nextDay, hoursList);
            }
        }
        return listOpenings;
    }

    private ArrayList<LocalTime> getHoursList(ArrayList<LocalTime> hours) {
        ArrayList<LocalTime> hoursList = new ArrayList<>();
        int hoursNumber = hours.get(1).getHour() - hours.get(0).getHour();
        for (int i = 0; i <= hoursNumber; i++) {
            LocalTime nextHour = hours.get(0).plusHours(i);
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

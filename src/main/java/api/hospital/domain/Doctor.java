package api.hospital.domain;


import api.hospital.domain.exceptions.TimeException;

import javax.persistence.Entity;
import javax.validation.constraints.Positive;
import java.time.LocalTime;


@Entity(name = "doctors")
public class Doctor extends Nurse{
    @Positive(message = "Experience is negative.")
    private Integer experience;
    private LocalTime startTime;
    private LocalTime endTime;

    public Doctor() {
    }

    public Doctor(String id, String name, String collegiateNumber, Integer experience) {
        super(id, name, collegiateNumber);
        this.experience = experience;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    @Override
    public LocalTime getStartTime() {
        return startTime;
    }

    @Override
    public LocalTime getEndTime() {
        return endTime;
    }

    @Override
    public void configurateSchedule(LocalTime startTimeParam, LocalTime endTimeParam) throws TimeException {
        if (startTimeParam.isAfter(endTimeParam)) throw new TimeException("start time can´t be after to end time.");
        this.startTime = startTimeParam;
        this.endTime = endTimeParam;
    }
}

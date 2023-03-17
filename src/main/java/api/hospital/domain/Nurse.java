package api.hospital.domain;

import api.hospital.domain.exceptions.TimeException;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;

@Entity(name = "nurses")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Nurse {
    @Id
    private String id;
    @NotNull(message = "Name is null.")
    @NotBlank(message = "Name is empty.")
    private String name;
    @NotNull(message = "Collegiate number is null.")
    @NotBlank(message = "Collegiate number is empty.")
    private String collegiateNumber;
    private LocalTime startTime;
    private LocalTime endTime;

    public Nurse() {
    }

    public Nurse(String id, String name, String collegiateNumber) {
        this.id = id;
        this.name = name;
        this.collegiateNumber = collegiateNumber;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCollegiateNumber() {
        return collegiateNumber;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void configurateSchedule(LocalTime startTimeParam, LocalTime endTimeParam) throws TimeException {
        if (startTimeParam.isAfter(endTimeParam)) throw new TimeException("start time can´t be after to end time.");
        this.startTime = startTimeParam;
        this.endTime = endTimeParam;
    }
}

package api.hospital.controller.dto;
import api.hospital.domain.Nurse;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class NurseInputDto {
    @NotNull(message = "Id is null.")
    @NotBlank(message = "Id is empty.")
    private String id;
    @NotNull(message = "Name is null.")
    @NotBlank(message = "Name is empty.")
    private String name;
    @NotNull(message = "Collegiate number is null.")
    @NotBlank(message = "Collegiate number is empty.")
    private String collegiateNumber;

    public NurseInputDto() {
    }

    public NurseInputDto(String id, String name, String collegiateNumber) {
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

    public void setCollegiateNumber(String collegiateNumber) {
        this.collegiateNumber = collegiateNumber;
    }

    public Nurse toDomain(){
        return new Nurse(this.id, this.name, this.collegiateNumber);
    }
}

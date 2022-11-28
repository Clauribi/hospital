package com.example.persistencia.controller.dto;

import com.example.persistencia.domain.Doctor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class DoctorInputDto {
    @NotNull(message = "Id is null.")
    @NotBlank(message = "Id is empty.")
    private String id;
    @NotNull(message = "Name is null.")
    @NotBlank(message = "Name is empty.")
    private String name;
    @NotNull(message = "Collegiate number is null.")
    @NotBlank(message = "Collegiate number is empty.")
    private String collegiateNumber;
    @Positive(message = "Experience is negative.")
    private Integer experience;

    public DoctorInputDto() {
    }

    public DoctorInputDto(String id, String name, String collegiateNumber, Integer experience) {
        this.id = id;
        this.name = name;
        this.collegiateNumber = collegiateNumber;
        this.experience = experience;
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

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public Doctor toDomain(){
        return new Doctor(this.id, this.name, this.collegiateNumber, this.experience);
    }
}

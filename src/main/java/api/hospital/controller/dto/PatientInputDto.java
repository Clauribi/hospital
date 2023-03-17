package api.hospital.controller.dto;

import api.hospital.domain.Patient;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PatientInputDto {
    @NotNull(message = "Id is null.")
    @NotBlank(message = "Id is empty.")
    private String id;
    @NotNull(message = "Name is null.")
    @NotBlank(message = "Name is empty.")
    private String name;
    @NotNull(message = "Address is null.")
    @NotBlank(message = "Address is empty.")
    private String address;

    public PatientInputDto() {
    }

    public PatientInputDto(String id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Patient toDomain(){
        return new Patient(this.id, this.name, this.address);
    }
}

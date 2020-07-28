package com.sg.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class EmployeeDto {
    private UUID employeeId;
    public String nom;
    public String prenom;
    //@TODO add more attributs
}

package com.sg.service.mapper;

import com.sg.domain.Employee;
import com.sg.service.dto.EmployeeDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/*
Classe de mapping Entity Dto Employee
 */
@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    EmployeeDto toDto(Employee employee);

    @Mapping(target = "employeeId", expression = "java(java.util.UUID.randomUUID())")
    @Mapping(target = "creationDate", ignore = true)
    @Mapping(target = "modificationDate", ignore = true)
    Employee toEntity(EmployeeDto employeeDto);
}

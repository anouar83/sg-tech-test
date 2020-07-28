package com.sg.service;

import com.sg.domain.Employee;
import com.sg.exceptions.DuplicateEntryException;
import com.sg.repository.EmployeeRepository;
import com.sg.service.dto.EmployeeDto;
import com.sg.service.mapper.EmployeeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service manager Employee
 */
@Service
public class EmployeeService {

    private static final Logger log = LoggerFactory.getLogger(EmployeeService.class);

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    public EmployeeService(EmployeeRepository employeeRepository,
                           EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
    }

    /**
     * find All Employees
     * @return {@link List<EmployeeDto>}
     */
    public List<EmployeeDto> findAll(){
        return employeeRepository.findAll()
                .stream()
                .map(e-> employeeMapper.toDto(e))
                .collect(Collectors.toList());
    }

    /**
     * get All Employees
     * @return {@link Page<EmployeeDto>}
     */
    public Page<EmployeeDto> getEmployees(Pageable pageable) {
        return employeeRepository.findAll(pageable).map(employeeMapper::toDto);
    }

    /**
     * Add the employee
     * @param employeeDto
     * @return {@link EmployeeDto}
     * @throws DuplicateEntryException
     */
    public EmployeeDto addEmployee(EmployeeDto employeeDto) throws DuplicateEntryException {
        log.debug("Service to add employee {}", employeeDto);
        Optional<Employee> employee = employeeRepository.findEmployeeByNom(employeeDto.nom);
        if(employee.isPresent()){
            throw new DuplicateEntryException("Employee" + employeeDto.toString() + "Exist en bas");
        }
        return employeeMapper.toDto(employeeRepository.save(employeeMapper.toEntity(employeeDto)));
    }
}

package com.sg.web.rest;

import com.sg.exceptions.DuplicateEntryException;
import com.sg.service.EmployeeService;
import com.sg.service.dto.EmployeeDto;
import com.sg.util.PaginationUtil;
import com.sg.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Optional;

/**
 * Rest controller for managing employees
 */
@RestController
@RequestMapping("/api/employee")
public class EmployeeResource {

    private static final Logger log = LoggerFactory.getLogger(EmployeeResource.class);

    private final EmployeeService employeeService;

    public EmployeeResource(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /**
     * {@code GET / Employee} : get all employees
     * @param pageable the pagination informatios.
     * @return the {@link ResponseEntity} with status {@code 200} and with body all employees if no search values,
     * employees matching the search if search value
     */
    @GetMapping("")
    public ResponseEntity<List<EmployeeDto>> getEmployees(Pageable pageable){
        Page<EmployeeDto> page = employeeService.getEmployees(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * {@code POST / EmployeeDto} : add EmployeeDto
     * @param employeeDto   EmployeeDto to add
     * @return the @{@link ResponseEntity} whith status {@code 200} and with body the Empoyee
     */
    @PostMapping("")
    public ResponseEntity<EmployeeDto> addUsers(@RequestBody EmployeeDto employeeDto){
        log.debug("Rest request to add employee {}", employeeDto);
        try {
            return ResponseUtil.wrapOrNotFound(Optional.ofNullable(employeeService.addEmployee(employeeDto)));
        } catch (DuplicateEntryException e) {
            return new ResponseEntity("Employee" +employeeDto.toString()+"Existe en base", new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }
    }
}

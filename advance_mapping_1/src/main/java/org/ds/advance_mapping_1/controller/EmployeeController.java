package org.ds.advance_mapping_1.controller;

import org.ds.advance_mapping_1.dto.EmployeeDTO;
import org.ds.advance_mapping_1.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public EmployeeDTO addEmployee(@RequestBody EmployeeDTO employeeDTO) {
        return employeeService.addEmployee(employeeDTO);
    }

    @GetMapping("/{employeeId}")
    public EmployeeDTO getEmployeeById(@PathVariable long employeeId){
        return employeeService.findByEmployeeId(employeeId);
    }

    @GetMapping
    public List<EmployeeDTO> getAllEmployee(){
        return employeeService.findByAllEmployee();
    }

    @PutMapping
    public EmployeeDTO updateEmployee(@RequestBody EmployeeDTO employeeDTO) {
        return employeeService.updateEmployee(employeeDTO);
    }

    @DeleteMapping("/{employeeId}")
    public EmployeeDTO deleteEmployeeById(@PathVariable long employeeId){
        return employeeService.deleteByEmployeeId(employeeId);
    }
}

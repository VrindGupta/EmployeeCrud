package com.vrind.employee.rest;

import com.vrind.employee.dto.EmployeeDTO;
import com.vrind.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeRestController {
    @Autowired
    EmployeeService employeeService;

    @Autowired
    public EmployeeRestController(EmployeeService theEmployeeService){
        employeeService = theEmployeeService;
    }

    @GetMapping("/")
    @Cacheable(value="employees")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees(){
        return ResponseEntity.ok().body(employeeService.findAll());
    }

    @GetMapping("/{empId}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable int empId){
        return ResponseEntity.ok().body(employeeService.findById(empId));
    }

    @PostMapping("/")
    public ResponseEntity<EmployeeDTO> addNewEmployee(@RequestBody EmployeeDTO employeeDTO){
        return  ResponseEntity.ok().body(employeeService.addEmployee(employeeDTO));
    }

    @PutMapping("/")
    public ResponseEntity<EmployeeDTO> updateEmployee(@RequestBody EmployeeDTO employeeDTO){
        return ResponseEntity.ok().body(employeeService.updateEmployee(employeeDTO));
    }

    @DeleteMapping("/{empId}")
    public ResponseEntity<EmployeeDTO> deleteEmployee(@PathVariable int empId){
        return ResponseEntity.ok().body(employeeService.deleteEmployee(empId));
    }
}

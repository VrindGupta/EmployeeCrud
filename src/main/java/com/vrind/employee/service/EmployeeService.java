package com.vrind.employee.service;

import com.vrind.employee.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeService {
    List<EmployeeDTO> findAll();

    EmployeeDTO findById(int id);

    EmployeeDTO addEmployee(EmployeeDTO employeeDTO);

    EmployeeDTO updateEmployee(EmployeeDTO employeeDTO);

    EmployeeDTO deleteEmployee(int empId);

}

package com.vrind.employee.service;

import com.vrind.employee.dao.EmployeeRepository;
import com.vrind.employee.dto.EmployeeDTO;
import com.vrind.employee.entity.Employee;
import com.vrind.employee.exception.EmployeeNotFoundException;
import com.vrind.employee.exception.EmployeeNotSavedException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository theEmployeeRepository){
        employeeRepository = theEmployeeRepository;
    }

    @Autowired
    private ModelMapper modelmapper;

    @Override
    public List<EmployeeDTO> findAll() {
        List<Employee> employeeList = employeeRepository.findAll();
        if(!employeeList.isEmpty()){
            return employeeList.stream().map(employee -> modelmapper.map(employee, EmployeeDTO.class))
                    .collect(Collectors.toList());
        }
        else{
            throw new EmployeeNotFoundException("No Employee exist");
        }
    }

    @Override
    public EmployeeDTO findById(int id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if(employee.isPresent()){
            return modelmapper.map(employee.get(), EmployeeDTO.class);
        }
        else{
            throw new EmployeeNotFoundException("No Employee with id = " + id);
        }
    }

    @Override
    public EmployeeDTO addEmployee(EmployeeDTO employeeDTO) {
        Employee employee = modelmapper.map(employeeDTO, Employee.class);
        Optional<Employee> dbEmployee = Optional.of(employeeRepository.save(employee));
        if(dbEmployee.isPresent()){
            return modelmapper.map(dbEmployee.get(),EmployeeDTO.class);
        }
        else{
            throw new EmployeeNotSavedException("Employee is not saved");
        }
    }

    @Override
    public EmployeeDTO updateEmployee(EmployeeDTO employeeDTO) {
        Optional<Employee> employee = employeeRepository.findById(employeeDTO.getId());
        if(employee.isPresent()){
            Employee temp = modelmapper.map(employee, Employee.class);
            temp.setName(employeeDTO.getName());
            temp.setSurname(employeeDTO.getSurname());
            temp.setEmail(employeeDTO.getEmail());
            Optional<Employee> dbEmployee = Optional.of(employeeRepository.save(temp));
            return modelmapper.map(dbEmployee.get(), EmployeeDTO.class);
        }
        else{
            throw new EmployeeNotFoundException("Employee is not Found");
        }
    }

    @Override
    public EmployeeDTO deleteEmployee(int empId) {
        Optional<Employee> dbEmployee = Optional.of(employeeRepository.findById(empId)).get();
        if(dbEmployee.isPresent()){
            employeeRepository.delete(dbEmployee.get());
            return modelmapper.map(dbEmployee.get(), EmployeeDTO.class);
        }
        else{
            throw new EmployeeNotFoundException("No Employee with id = " + empId);
        }
    }
}

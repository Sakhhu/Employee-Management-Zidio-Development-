package com.EMPMANAGE.Service;

import com.EMPMANAGE.Entity.Employee;
import com.EMPMANAGE.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    // 1. list of all employees
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // 2. save new employee
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    // 3. search employee from id
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    // 4. delete employee from id
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
}
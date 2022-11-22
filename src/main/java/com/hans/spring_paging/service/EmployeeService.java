package com.hans.spring_paging.service;

import com.hans.spring_paging.formRequest.EmployeeFillterForm;
import com.hans.spring_paging.model.Employee;
import com.hans.spring_paging.model.EmployeePage;
import com.hans.spring_paging.model.EmployeeSearchCriteria;
import com.hans.spring_paging.repository.EmployeeRepository;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EmployeeService {

    Page<Employee> getEmployee(EmployeePage employeePage, EmployeeSearchCriteria employeeSearchCriteria);
    Employee addEmployee(Employee employee);
    List<Employee> getAll();
    List<Employee> getFillterAI();
    List<Employee> employeeFillter(EmployeeFillterForm employeeFillterForm);
    Page<Employee> getFillterPaging(EmployeePage employeePage, EmployeeFillterForm employeeFillterForm);
}

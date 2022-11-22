package com.hans.spring_paging.service;

import com.hans.spring_paging.formRequest.EmployeeFillterForm;
import com.hans.spring_paging.model.Employee;
import com.hans.spring_paging.model.EmployeePage;
import com.hans.spring_paging.model.EmployeeSearchCriteria;
import com.hans.spring_paging.repository.EmployeeCriteriaRepository;
import com.hans.spring_paging.repository.EmployeeFillterRepository;
import com.hans.spring_paging.repository.EmployeePagingRepository;
import com.hans.spring_paging.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor @Service
public class EmployeeServiceImpl implements EmployeeService{
    private final EmployeeRepository employeeRepository;
    private final EmployeeCriteriaRepository employeeCriteriaRepository;
    private final EmployeeFillterRepository employeeFillterRepository;
    private final EmployeePagingRepository employeePagingRepository;
    @Override
    public Page<Employee> getEmployee(EmployeePage employeePage, EmployeeSearchCriteria employeeSearchCriteria) {
        return employeeCriteriaRepository.findAllWithFillter(employeePage,employeeSearchCriteria);
    }

    @Override
    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    @Override
    public List<Employee> getFillterAI() {
        return employeeCriteriaRepository.Fillter();
    }

    @Override
    public List<Employee> employeeFillter(EmployeeFillterForm employeeFillterForm) {
        return employeeFillterRepository.employeeFillter(employeeFillterForm);
    }

    @Override
    public Page<Employee> getFillterPaging(EmployeePage employeePage, EmployeeFillterForm employeeFillterForm) {
        return employeePagingRepository.fillterWithPaging(employeePage,employeeFillterForm);
    }
}

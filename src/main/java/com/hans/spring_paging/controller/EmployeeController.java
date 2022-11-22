package com.hans.spring_paging.controller;

import com.hans.spring_paging.formRequest.EmployeeFillterForm;
import com.hans.spring_paging.model.Employee;
import com.hans.spring_paging.model.EmployeePage;
import com.hans.spring_paging.model.EmployeeSearchCriteria;
import com.hans.spring_paging.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping("/employeePage/{pageNumber}/{sortby}")
    public ResponseEntity<Page<Employee>> getEmployee(EmployeePage employeePage,@RequestBody EmployeeSearchCriteria employeeSearchCriteria){
        return new ResponseEntity<>(employeeService.getEmployee(employeePage,employeeSearchCriteria), HttpStatus.OK);
    }

    @PostMapping("/addemployee")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee){
        return new ResponseEntity<>(employeeService.addEmployee(employee),HttpStatus.OK);
    }

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAll(){
        return new ResponseEntity<>(employeeService.getAll(),HttpStatus.OK);
    }

    @GetMapping("/fillterAI")
    public ResponseEntity<List<Employee>> getFillterAI(){
        return new ResponseEntity<>(employeeService.getFillterAI(),HttpStatus.OK);
    }

    @GetMapping("/employeeFillter")
    public ResponseEntity<List<Employee>> employeeFillter(
            @RequestParam(name = "status", required = false) String status,
            @RequestParam(name = "departmentName", required = false) Set<String> departmentName,
            @RequestParam(name = "search", required = false) String search
            ){
        log.info("status = {}",status);
        if(departmentName != null){
            for (String name : departmentName){
                log.info("department name : {}",name);
            }
        }
        EmployeeFillterForm employeeFillterForm = new EmployeeFillterForm(departmentName,status,search);
        return ResponseEntity.ok(employeeService.employeeFillter(employeeFillterForm));
    }

    @GetMapping("/fillterWithPaging")
    public ResponseEntity<Page<Employee>> fillterWithPaging(
            @RequestParam(name = "status", required = false) String status,
            @RequestParam(name = "departmentName", required = false) Set<String> departmentName,
            @RequestParam(name = "search", required = false) String search,
            EmployeePage employeePage
    ){
        log.info("status = {}",status);
        if(departmentName != null){
            for (String name : departmentName){
                log.info("department name : {}",name);
            }
        }
        EmployeeFillterForm employeeFillterForm = new EmployeeFillterForm(departmentName,status,search);
        return ResponseEntity.ok(employeeService.getFillterPaging(employeePage,employeeFillterForm));
    }
}

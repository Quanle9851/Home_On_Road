package com.hans.spring_paging.controller;

import com.hans.spring_paging.model.Department;
import com.hans.spring_paging.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/{name}")
    public ResponseEntity<List<Department>> getByName(@PathVariable String name) {
        return new ResponseEntity<>(departmentService.searchByName(name), HttpStatus.OK);
    }

}

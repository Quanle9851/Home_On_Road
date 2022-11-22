package com.hans.spring_paging.service;

import com.hans.spring_paging.model.Department;
import com.hans.spring_paging.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService{
    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public List<Department> getAll() {
        return departmentRepository.findAll();
    }

    @Override
    public List<Department> searchByName(String name) {
        return departmentRepository.findByName(name);
    }
}

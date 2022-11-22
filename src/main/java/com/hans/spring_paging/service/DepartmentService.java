package com.hans.spring_paging.service;

import com.hans.spring_paging.model.Department;

import java.util.List;

public interface DepartmentService {
    List<Department> getAll();
    List<Department> searchByName(String name);
}

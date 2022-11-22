package com.hans.spring_paging.repository;

import com.hans.spring_paging.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.xml.namespace.QName;
import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department,Long> {
     List<Department> findByName(String name);
}

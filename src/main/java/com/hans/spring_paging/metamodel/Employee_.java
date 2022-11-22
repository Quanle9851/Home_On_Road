package com.hans.spring_paging.metamodel;

import com.hans.spring_paging.model.Department;
import com.hans.spring_paging.model.Employee;


import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Employee.class)
public abstract class Employee_ {
    public static volatile SingularAttribute<Employee,Long> id;
    public static volatile SingularAttribute<Employee,String> firstName;
    public static volatile SingularAttribute<Employee,String> lastName;
    public static volatile SingularAttribute<Employee,String> address;
    public static volatile SingularAttribute<Employee,String> status;
    public static volatile SingularAttribute<Employee, Department> department;

    public static final String ID = "id";
    public static final String FIRST_NAME = "firstName";
    public static final String LAST_NAME = "lastName";
    public static final String ADDRESS = "address";
    public static final String DEPARTMENT = "department";
    public static final String STATUS = "status";
}

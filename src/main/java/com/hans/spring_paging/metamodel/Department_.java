package com.hans.spring_paging.metamodel;

import com.hans.spring_paging.model.Department;


import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Department.class)
public abstract class Department_ {
    public static volatile SingularAttribute<Department,Long> id;
    public static volatile SingularAttribute<Department,String> name;

    public static final String ID = "id";
    public static final String NAME = "name";
}

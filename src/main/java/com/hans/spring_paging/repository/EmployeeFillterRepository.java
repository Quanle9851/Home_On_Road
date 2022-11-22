package com.hans.spring_paging.repository;

import com.hans.spring_paging.formRequest.EmployeeFillterForm;
import com.hans.spring_paging.model.Department;
import com.hans.spring_paging.model.Department_;
import com.hans.spring_paging.model.Employee;
import com.hans.spring_paging.model.Employee_;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeFillterRepository {
    private final EntityManager entityManager;
    private final CriteriaBuilder criteriaBuilder;

    public EmployeeFillterRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    public List<Employee> employeeFillter(EmployeeFillterForm employeeFillterForm){
        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
        Root<Employee> employeeRoot = criteriaQuery.from(Employee.class);
        Join<Employee, Department> departmentJoin = employeeRoot.join(Employee_.department);
        Predicate predicate = getPredicate(employeeRoot,departmentJoin,employeeFillterForm);
        criteriaQuery.where(predicate);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    private Predicate getPredicate(Root<Employee> employeeRoot, Join<Employee, Department> departmentJoin, EmployeeFillterForm employeeFillterForm) {
        List<Predicate> totalPredicate = new ArrayList<>();
        if(employeeFillterForm.getDepartmentName()!=null){
            List<Predicate> predicates = new ArrayList<>();
            for (String nameDepart : employeeFillterForm.getDepartmentName()){
                predicates.add(criteriaBuilder.equal(departmentJoin.get(Department_.name), nameDepart));
            }
            totalPredicate.add(criteriaBuilder.or(predicates.toArray(new Predicate[0])));
        }
        if(employeeFillterForm.getStatus()!=null){
            totalPredicate.add(criteriaBuilder.equal(employeeRoot.get(Employee_.status), employeeFillterForm.getStatus()));
        }
        return criteriaBuilder.and(totalPredicate.toArray(new Predicate[0]));
    }

}

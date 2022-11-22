package com.hans.spring_paging.repository;

import com.hans.spring_paging.formRequest.EmployeeFillterForm;
import com.hans.spring_paging.model.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeePagingRepository {
    private final EntityManager entityManager;
    private final CriteriaBuilder criteriaBuilder;

    public EmployeePagingRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    public Page<Employee> fillterWithPaging(EmployeePage employeePage, EmployeeFillterForm employeeFillterForm){
        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
        Root<Employee> employeeRoot = criteriaQuery.from(Employee.class);
        Join<Employee, Department> departmentJoin = employeeRoot.join(Employee_.department);
        Predicate predicate = getPredicate(employeeRoot,departmentJoin,employeeFillterForm);
        criteriaQuery.where(predicate);

        TypedQuery<Employee> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult(employeePage.getPageNumber() * employeePage.getPageSize());
        typedQuery.setMaxResults(employeePage.getPageSize());

        Pageable pageable = getPageable(employeePage);

        Long employeeCount = getEmployeeCount(predicate);

        return new PageImpl<>(typedQuery.getResultList(), pageable, employeeCount);

    }

    private Long getEmployeeCount(Predicate predicate) {
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<Employee> countRoot = countQuery.from(Employee.class);
        Join<Employee,Department> departmentJoin = countRoot.join(Employee_.department);
        countQuery.select(criteriaBuilder.count(countRoot)).where(predicate);
        return entityManager.createQuery(countQuery).getSingleResult();
    }

    private Pageable getPageable(EmployeePage employeePage) {
        return PageRequest.of(employeePage.getPageNumber(),employeePage.getPageSize());
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
        if(employeeFillterForm.getSearch()!=null){
            totalPredicate.add(criteriaBuilder.like(employeeRoot.get(Employee_.firstName), "%" + employeeFillterForm.getSearch() + "%"));
        }
        return criteriaBuilder.and(totalPredicate.toArray(new Predicate[0]));
    }
}

package com.hans.spring_paging.repository;


import com.hans.spring_paging.model.*;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class EmployeeCriteriaRepository {
    private final EntityManager entityManager;
    private final CriteriaBuilder criteriaBuilder;

    public EmployeeCriteriaRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    public List<Employee> Fillter(){
        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
        Root<Employee> employeeRoot = criteriaQuery.from(Employee.class);
        Join<Employee,Department> departmentJoin = employeeRoot.join(Employee_.department);
//        criteriaQuery.where(criteriaBuilder.equal(departmentJoin.get("name"), "AI"));
        Predicate predicate = getPredicateDepartment(departmentJoin,employeeRoot);
        criteriaQuery.where(predicate);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    private Predicate getPredicateDepartment(Join<Employee,Department> departmentJoin, Root<Employee> employeeRoot) {
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(criteriaBuilder.like(employeeRoot.get(Employee_.firstName), "%H%"));
        predicates.add(criteriaBuilder.equal(departmentJoin.get(Department_.name), "AI"));
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    public Page<Employee> findAllWithFillter(EmployeePage employeePage, EmployeeSearchCriteria employeeSearchCriteria){
        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
        Root<Employee> employeeRoot = criteriaQuery.from(Employee.class);
        Predicate predicate = getPredicate(employeeSearchCriteria,employeeRoot);
        criteriaQuery.where(predicate);
        setOrder(employeePage, criteriaQuery,employeeRoot);

        TypedQuery<Employee> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult(employeePage.getPageNumber() * employeePage.getPageSize());
        typedQuery.setMaxResults(employeePage.getPageSize());

        Pageable pageable = getPageable(employeePage);

        Long employeeCount = getEmployeeCount(predicate);

        return new PageImpl<>(typedQuery.getResultList(), pageable, employeeCount);
    }



    private Predicate getPredicate(EmployeeSearchCriteria employeeSearchCriteria, Root<Employee> employeeRoot) {
        List<Predicate> predicates = new ArrayList<>();
        try {
            if (Objects.nonNull(employeeSearchCriteria.getFirstName())) {
                predicates.add(
                        criteriaBuilder.like(employeeRoot.get("firstName"), "%" + employeeSearchCriteria.getFirstName() + "%")
                );
            }

            if (Objects.nonNull(employeeSearchCriteria.getLastName())) {
                predicates.add(
                        criteriaBuilder.like(employeeRoot.get("lastName"), "%" + employeeSearchCriteria.getLastName() + "%")
                );
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }


    private void setOrder(EmployeePage employeePage, CriteriaQuery<Employee> criteriaQuery, Root<Employee> employeeRoot) {
        if(employeePage.getSortDirection().equals(Sort.Direction.ASC)){
            criteriaQuery.orderBy(criteriaBuilder.asc(employeeRoot.get(employeePage.getSortby())));
        } else {
            criteriaQuery.orderBy(criteriaBuilder.desc(employeeRoot.get(employeePage.getSortby())));
        }
    }

    private Pageable getPageable(EmployeePage employeePage) {
        Sort sort = Sort.by(employeePage.getSortDirection(), employeePage.getSortby());
        return PageRequest.of(employeePage.getPageNumber(),employeePage.getPageSize(),sort);
    }

    private Long getEmployeeCount(Predicate predicate) {
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<Employee> countRoot = countQuery.from(Employee.class);
        countQuery.select(criteriaBuilder.count(countRoot)).where(predicate);
        return entityManager.createQuery(countQuery).getSingleResult();
    }
}

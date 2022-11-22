package com.hans.spring_paging.formRequest;

import lombok.*;

import java.util.Set;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeFillterForm {
    private Set<String> departmentName;
    private String status;
    private String search;
}

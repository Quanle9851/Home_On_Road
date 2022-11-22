package com.hans.spring_paging.DTO;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {
    private Long id;
    private String fisrtName;
    private String lastName;
    private String address;
    private String status;
}

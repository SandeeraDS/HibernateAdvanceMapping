package org.ds.advance_mapping_1.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class EmployeeDTO {
    private long id;
    private String firstName;
    private String lastName;
    private String email;
}

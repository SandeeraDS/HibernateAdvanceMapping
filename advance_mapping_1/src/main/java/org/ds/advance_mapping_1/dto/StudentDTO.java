package org.ds.advance_mapping_1.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class StudentDTO {
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private List<CourseDTO> courseDTOList;
}

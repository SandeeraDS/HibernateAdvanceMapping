package org.ds.advance_mapping_1.dto;


import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CourseDTO {
    private long courseId;
    private String title;
    private List<ReviewDTO> reviewDTOS;
    private List<StudentDTO> studentDTOS;
}

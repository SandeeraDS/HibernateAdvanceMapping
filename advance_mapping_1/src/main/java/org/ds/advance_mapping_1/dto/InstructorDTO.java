package org.ds.advance_mapping_1.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class InstructorDTO {
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private long instructorDetailsId;
    private String youtubeChannel;
    private String hobby;
    List<CourseDTO> courseDTOList;
}

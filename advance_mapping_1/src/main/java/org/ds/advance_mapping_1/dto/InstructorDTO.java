package org.ds.advance_mapping_1.dto;

import lombok.*;

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
    private String youtubeChannel;
    private String hobby;
}

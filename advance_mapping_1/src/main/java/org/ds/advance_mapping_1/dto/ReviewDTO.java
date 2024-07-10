package org.ds.advance_mapping_1.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ReviewDTO {
    private long id;
    private long courseId;
    private String comment;
}

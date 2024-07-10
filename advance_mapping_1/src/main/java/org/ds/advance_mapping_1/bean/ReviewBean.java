package org.ds.advance_mapping_1.bean;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "REVIEW")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReviewBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;
    @Column(name = "COMMENT")
    private String comment;
    @Column(name = "COURSE_ID")
    private long courseId;

}

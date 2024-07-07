package org.ds.advance_mapping_1.bean;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "INSTRUCTOR_DETAILS")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class InstructorDetailsBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;
    @Column(name = "YOUTUBE_CHANNEL")
    private String youtubeChannel;
    @Column(name = "HOBBY")
    private String hobby;
}

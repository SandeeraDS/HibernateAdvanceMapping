package org.ds.advance_mapping_1.bean;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "INSTRUCTOR")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class InstructorBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "EMAIL")
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "INSTRUCTOR_DETAIL_ID")
    private InstructorDetailsBean instructorDetailsBean;

    @OneToMany(mappedBy = "instructorBean",
            cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
    private List<CourseBean> courseBeanList;

    public void add(CourseBean courseBean) {
        if (courseBeanList == null) {
            courseBeanList = new ArrayList<>();
        }
        courseBeanList.add(courseBean);
        courseBean.setInstructorBean(this);
    }
}

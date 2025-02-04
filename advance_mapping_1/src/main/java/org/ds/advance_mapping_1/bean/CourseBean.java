package org.ds.advance_mapping_1.bean;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "COURSE")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CourseBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;
    @Column(name = "TITLE")
    private String title;
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn(name = "INSTRUCTOR_ID")
    private InstructorBean instructorBean;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "COURSE_ID")
    private List<ReviewBean> reviewBeans;
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
    @JoinTable(name = "COURSE_STUDENT",
            joinColumns = @JoinColumn(name = "COURSE_ID"),
            inverseJoinColumns = @JoinColumn(name = "STUDENT_ID"))
    private List<StudentBean> studentBeanList;

    public void addReview(ReviewBean reviewBean) {
        if (reviewBeans == null) {
            reviewBeans = new ArrayList<>();
        }
        reviewBean.setCourseId(this.id);
        reviewBeans.add(reviewBean);
    }

    public void addStudent(StudentBean studentBean) {
        if (studentBeanList == null) {
            studentBeanList = new ArrayList<>();
        }
        studentBeanList.add(studentBean);
    }
}

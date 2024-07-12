package org.ds.advance_mapping_1.utils;

import org.ds.advance_mapping_1.bean.CourseBean;
import org.ds.advance_mapping_1.dto.CourseDTO;
import org.ds.advance_mapping_1.dto.ReviewDTO;
import org.ds.advance_mapping_1.dto.StudentDTO;

import java.util.stream.Collectors;

public class CoursePopulator {
    public static CourseDTO populateCourseDTO(CourseBean courseBean) {
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setCourseId(courseBean.getId());
        courseDTO.setTitle(courseBean.getTitle());
        if (courseBean.getReviewBeans() != null) {
            courseDTO.setReviewDTOS(courseBean.getReviewBeans().stream().map(c -> new ReviewDTO(c.getId(), c.getCourseId(), c.getComment())).collect(Collectors.toList()));
        }
        if (courseBean.getStudentBeanList() != null) {
            courseDTO.setStudentDTOS(courseBean.getStudentBeanList().stream().map(c -> new StudentDTO(c.getId(), c.getFirstName(), c.getLastName(), c.getEmail(), null)).collect(Collectors.toList()));
        }
        return courseDTO;
    }

}

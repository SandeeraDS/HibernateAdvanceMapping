package org.ds.advance_mapping_1.utils;

import org.ds.advance_mapping_1.bean.CourseBean;
import org.ds.advance_mapping_1.bean.InstructorBean;
import org.ds.advance_mapping_1.dto.CourseDTO;
import org.ds.advance_mapping_1.dto.InstructorDTO;
import org.ds.advance_mapping_1.dto.ReviewDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CoursePopulator {
    public static CourseDTO populateCourseDTO(CourseBean courseBean) {
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setCourseId(courseBean.getId());
        courseDTO.setTitle(courseBean.getTitle());
        if (courseBean.getReviewBeans() != null) {
            courseDTO.setReviewDTOS(courseBean.getReviewBeans().stream().map(c -> {
                return new ReviewDTO(c.getId(), c.getCourseId(), c.getComment());
            }).collect(Collectors.toList()));
        }
        return courseDTO;
    }

}

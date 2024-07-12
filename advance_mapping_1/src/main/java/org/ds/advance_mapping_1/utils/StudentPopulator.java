package org.ds.advance_mapping_1.utils;

import org.ds.advance_mapping_1.bean.StudentBean;
import org.ds.advance_mapping_1.dto.CourseDTO;
import org.ds.advance_mapping_1.dto.StudentDTO;

import java.util.stream.Collectors;

public class StudentPopulator {

    public static StudentDTO populateStudentDTO(StudentBean studentBean) {
        StudentDTO studentDTO = new StudentDTO(studentBean.getId(), studentBean.getFirstName(), studentBean.getLastName(), studentBean.getEmail(), null);
        if (studentBean.getCourseBeanList() != null) {
            studentDTO.setCourseDTOList(studentBean.getCourseBeanList().stream().map(c -> new CourseDTO(c.getId(), c.getTitle(), null, null)).collect(Collectors.toList()));
        }
        return studentDTO;
    }

    public static StudentBean populateStudentBean(StudentDTO studentDTO, boolean populateId) {
        StudentBean studentBean = new StudentBean();
        if (populateId) {
            studentBean.setId(studentDTO.getId());
        }
        studentBean.setFirstName(studentDTO.getFirstName());
        studentBean.setLastName(studentDTO.getLastName());
        studentBean.setEmail(studentDTO.getEmail());
        return studentBean;
    }


}

package org.ds.advance_mapping_1.utils;

import org.ds.advance_mapping_1.bean.CourseBean;
import org.ds.advance_mapping_1.bean.InstructorBean;
import org.ds.advance_mapping_1.bean.InstructorDetailsBean;
import org.ds.advance_mapping_1.dto.CourseDTO;
import org.ds.advance_mapping_1.dto.InstructorDTO;

import java.util.ArrayList;
import java.util.List;

public class InstructorPopulator {

    public static InstructorDTO populateInstructorDTO(InstructorBean instructorBean) {
        InstructorDTO instructorDTO = new InstructorDTO();
        instructorDTO.setId(instructorBean.getId());
        instructorDTO.setFirstName(instructorBean.getFirstName());
        instructorDTO.setLastName(instructorBean.getLastName());
        instructorDTO.setEmail(instructorBean.getEmail());
        if (instructorBean.getInstructorDetailsBean() != null) {
            instructorDTO.setInstructorDetailsId(instructorBean.getInstructorDetailsBean().getId());
            instructorDTO.setYoutubeChannel(instructorBean.getInstructorDetailsBean().getYoutubeChannel());
            instructorDTO.setHobby(instructorBean.getInstructorDetailsBean().getHobby());
        }
        List<CourseDTO> courseDTOList = new ArrayList<>();
        if (instructorBean.getCourseBeanList() != null) {
            for (CourseBean courseBean : instructorBean.getCourseBeanList()) {
                courseDTOList.add(new CourseDTO(courseBean.getId(), courseBean.getTitle()));
            }
        }
        instructorDTO.setCourseDTOList(courseDTOList);
        return instructorDTO;
    }

    public static InstructorBean populateInstructorBean(InstructorDTO instructorDTO, boolean populateId) {
        InstructorBean instructorBean = new InstructorBean();
        InstructorDetailsBean instructorDetailsBean = new InstructorDetailsBean();
        if (populateId) {
            instructorBean.setId(instructorDTO.getId());
        }
        instructorBean.setFirstName(instructorDTO.getFirstName());
        instructorBean.setLastName(instructorDTO.getLastName());
        instructorBean.setEmail(instructorDTO.getEmail());

        if ((instructorDTO.getYoutubeChannel() != null && !instructorDTO.getYoutubeChannel().isBlank()) ||
                (instructorDTO.getHobby() != null && !instructorDTO.getHobby().isBlank())) {
            instructorDetailsBean.setYoutubeChannel(instructorDTO.getYoutubeChannel());
            instructorDetailsBean.setHobby(instructorDTO.getHobby());
            instructorBean.setInstructorDetailsBean(instructorDetailsBean);
        }
        return instructorBean;
    }

    public static InstructorDTO populateInstructorDTO(InstructorDetailsBean instructorDetailsBean) {
        InstructorBean instructorBean = instructorDetailsBean.getInstructorBean();
        InstructorDTO instructorDTO = new InstructorDTO();
        instructorDTO.setId(instructorBean.getId());
        instructorDTO.setFirstName(instructorBean.getFirstName());
        instructorDTO.setLastName(instructorBean.getLastName());
        instructorDTO.setEmail(instructorBean.getEmail());
        instructorDTO.setInstructorDetailsId(instructorDetailsBean.getId());
        instructorDTO.setYoutubeChannel(instructorDetailsBean.getYoutubeChannel());
        instructorDTO.setHobby(instructorDetailsBean.getHobby());
        return instructorDTO;
    }

}

package org.ds.advance_mapping_1.service;

import jakarta.transaction.Transactional;
import org.ds.advance_mapping_1.Exception.ClientException;
import org.ds.advance_mapping_1.Exception.ServerException;
import org.ds.advance_mapping_1.bean.CourseBean;
import org.ds.advance_mapping_1.bean.InstructorBean;
import org.ds.advance_mapping_1.bean.ReviewBean;
import org.ds.advance_mapping_1.dto.CourseDTO;

import org.ds.advance_mapping_1.dto.ReviewDTO;
import org.ds.advance_mapping_1.repository.CourseRepository;
import org.ds.advance_mapping_1.utils.CoursePopulator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Transactional
    public CourseDTO addCourse(CourseDTO courseDTO) {

        if (courseDTO == null || courseDTO.getTitle() == null || courseDTO.getTitle().isBlank()) {
            throw new ClientException("invalid input data.");
        }
        try {
            CourseBean courseBean = new CourseBean();
            courseBean.setTitle(courseDTO.getTitle());
            courseRepository.persist(courseBean);
            courseDTO.setCourseId(courseBean.getId());
            return courseDTO;
        } catch (Exception e) {
            throw new ServerException("Error occurred when adding a new course.");
        }
    }


    @Transactional
    public CourseDTO findByCourseId(long id) {
        if (id > 0) {
            CourseBean courseBean;
            try {
                courseBean = courseRepository.getById(id);
            } catch (Exception e) {
                throw new ServerException("Error occurred when getting course.");
            }
            if (courseBean != null) {
                return CoursePopulator.populateCourseDTO(courseBean);
            } else {
                throw new ClientException("Course not found for given id.");
            }
        } else {
            throw new ClientException("invalid Course Id.");
        }
    }

    @Transactional
    public List<CourseDTO> findByAllCourse() {
        try {
            List<CourseBean> courseBeanList = courseRepository.getAll();
            return courseBeanList.stream().map(CoursePopulator::populateCourseDTO).collect(Collectors.toList());
        } catch (Exception e) {
            throw new ServerException("Error occurred when getting all courses.");
        }
    }

    @Transactional
    public CourseDTO addReview(long courseId, ReviewDTO reviewDTO) {
        if (courseId <= 0) {
            throw new ClientException("Invalid course id.");
        }
        if (reviewDTO == null || reviewDTO.getComment() == null || reviewDTO.getComment().isBlank()) {
            throw new ClientException("Invalid review.");
        }

        CourseBean courseBean;
        try {
            courseBean = courseRepository.getById(courseId);
        } catch (Exception e) {
            throw new ServerException("Error occurred when getting course.");
        }

        ReviewBean reviewBean = new ReviewBean();
        reviewBean.setComment(reviewDTO.getComment());

        try {
            courseBean.addReview(reviewBean);
            courseRepository.merge(courseBean);
        } catch (Exception e) {
            throw new ServerException("Error occurred when adding course to course");
        }

        return CoursePopulator.populateCourseDTO(courseBean);
    }

    @Transactional
    public CourseDTO deleteCourseById(long courseId) {
        if (courseId > 0) {
            CourseBean courseBean;
            try {
                courseBean = courseRepository.getById(courseId);
            } catch (Exception e) {
                throw new ServerException("Error occurred when getting course.");
            }
            if (courseBean != null) {
                try {
                    courseRepository.delete(courseBean);
                    return CoursePopulator.populateCourseDTO(courseBean);
                } catch (Exception e){
                    throw new ClientException("Error occurred when removing course.");
                }

            } else {
                throw new ClientException("Course not found for given id.");
            }
        } else {
            throw new ClientException("invalid Course Id.");
        }
    }
}

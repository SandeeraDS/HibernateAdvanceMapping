package org.ds.advance_mapping_1.service;

import jakarta.transaction.Transactional;
import org.ds.advance_mapping_1.Exception.ClientException;
import org.ds.advance_mapping_1.Exception.ServerException;
import org.ds.advance_mapping_1.bean.CourseBean;
import org.ds.advance_mapping_1.bean.StudentBean;
import org.ds.advance_mapping_1.dto.CourseDTO;
import org.ds.advance_mapping_1.dto.StudentDTO;
import org.ds.advance_mapping_1.repository.StudentRepository;
import org.ds.advance_mapping_1.utils.StudentPopulator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Transactional
    public StudentDTO addStudent(StudentDTO newStudent) {

        if (newStudent == null || newStudent.getFirstName() == null || newStudent.getFirstName().isBlank()
                || newStudent.getLastName() == null || newStudent.getLastName().isBlank()
                || newStudent.getEmail() == null || newStudent.getEmail().isBlank()) {
            throw new ClientException("invalid input data.");
        }

        try {
            StudentBean studentBean = StudentPopulator.populateStudentBean(newStudent, false);
            studentRepository.save(studentBean);
            newStudent.setId(studentBean.getId());
            return newStudent;
        } catch (Exception e) {
            throw new ServerException("Error occurred when adding a new student.");
        }
    }

    @Transactional
    public StudentDTO findStudentAndCoursesByStudentId(long studentId) {
        if (studentId > 0) {
            StudentBean studentBean;
            try {
                studentBean = studentRepository.findStudentAndCoursesByStudentId(studentId);
            } catch (Exception e) {
                throw new ServerException("Error occurred when getting student.");
            }
            if (studentBean != null) {
                return StudentPopulator.populateStudentDTO(studentBean);
            } else {
                throw new ClientException("Course not found for given id.");
            }
        } else {
            throw new ClientException("invalid Course Id.");
        }
    }

    @Transactional
    public StudentDTO addCourseByStudentId(long studentId, List<CourseDTO> courseDTOList) {

        if (studentId > 0) {
            for (CourseDTO courseDTO : courseDTOList) {
                if (courseDTO == null || courseDTO.getTitle() == null || courseDTO.getTitle().isBlank()) {
                    throw new ClientException("invalid input data.");
                }
            }

            StudentBean studentBean;
            try {
                studentBean = studentRepository.findById(studentId);
            } catch (Exception e) {
                throw new ServerException("Error occurred when getting Student.");
            }

            if (studentBean == null) {
                throw new ServerException("Student not found for given id.");
            }

            for (CourseDTO courseDTO : courseDTOList) {
                CourseBean courseBean = new CourseBean();
                courseBean.setTitle(courseDTO.getTitle());
                studentBean.addCourse(courseBean);
                try {
                    studentRepository.merge(studentBean);
                } catch (Exception e) {
                    throw new ServerException("Error occurred when saving course with student.");
                }
            }

            return StudentPopulator.populateStudentDTO(studentBean);
        } else {
            throw new ClientException("invalid Student Id.");
        }
    }

    @Transactional
    public StudentDTO deleteStudentById(long studentId) {
        if (studentId > 0) {
            StudentBean studentBean;
            try {
                studentBean = studentRepository.findById(studentId);
            } catch (Exception e) {
                throw new ServerException("Error occurred when finding student.");
            }
            if (studentBean != null) {
                StudentDTO studentDTO = StudentPopulator.populateStudentDTO(studentBean);
                try {
                    studentRepository.delete(studentBean);
                } catch (Exception e) {
                    throw new ServerException("Error occurred when deleting student.");
                }
                return studentDTO;
            } else {
                throw new ClientException("Student not found for given id.");
            }
        } else {
            throw new ClientException("invalid Student Id.");
        }
    }
}

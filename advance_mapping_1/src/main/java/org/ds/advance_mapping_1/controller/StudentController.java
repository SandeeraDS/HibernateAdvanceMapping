package org.ds.advance_mapping_1.controller;

import org.ds.advance_mapping_1.dto.CourseDTO;
import org.ds.advance_mapping_1.dto.StudentDTO;
import org.ds.advance_mapping_1.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public StudentDTO addStudent(@RequestBody StudentDTO studentDTO) {
        return studentService.addStudent(studentDTO);
    }

    @DeleteMapping("/{studentId}")
    public StudentDTO deleteStudentById(@PathVariable long studentId) {
        return studentService.deleteStudentById(studentId);
    }

    @PostMapping("/courses/{studentId}")
    public StudentDTO addCourseByStudentId(@PathVariable long studentId, @RequestBody List<CourseDTO> courseDTOList) {
        return studentService.addCourseByStudentId(studentId, courseDTOList);
    }

    @GetMapping("/StudentCourse/{studentId}")
    public StudentDTO findStudentAndCoursesByStudentId(@PathVariable long studentId) {
        return studentService.findStudentAndCoursesByStudentId(studentId);
    }
}

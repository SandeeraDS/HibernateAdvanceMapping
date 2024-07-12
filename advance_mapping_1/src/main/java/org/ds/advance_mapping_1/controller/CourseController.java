package org.ds.advance_mapping_1.controller;

import org.ds.advance_mapping_1.dto.CourseDTO;
import org.ds.advance_mapping_1.dto.ReviewDTO;
import org.ds.advance_mapping_1.dto.StudentDTO;
import org.ds.advance_mapping_1.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/courses")
public class CourseController {
    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService){
        this.courseService = courseService;
    }

    @GetMapping("/{courseId}")
    public CourseDTO getCourseById(@PathVariable long courseId){
        return courseService.findByCourseId(courseId);
    }

    @GetMapping
    public List<CourseDTO> getAllCourse(){
        return courseService.findByAllCourse();
    }

    @PostMapping
    public CourseDTO addCourse(@RequestBody CourseDTO courseDTO) {
        return courseService.addCourse(courseDTO);
    }

    @PostMapping("/review/{courseId}")
    public CourseDTO addReview(@PathVariable long courseId,  @RequestBody ReviewDTO reviewDTO) {
        return courseService.addReview(courseId,reviewDTO);
    }

    @DeleteMapping("/{courseId}")
    public CourseDTO deleteCourseById(@PathVariable long courseId){
        return courseService.deleteCourseById(courseId);
    }

    @PostMapping("/Student/{courseId}")
    public CourseDTO addStudent(@PathVariable long courseId,  @RequestBody StudentDTO studentDTO) {
        return courseService.addStudentByCourseId(courseId,studentDTO);
    }

    @GetMapping("/CourseStudent/{courseId}")
    public CourseDTO getCourseAndStudentByCourseId(@PathVariable long courseId){
        return courseService.findCourseAndStudentByCourseId(courseId);
    }

}

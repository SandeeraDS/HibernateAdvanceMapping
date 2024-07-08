package org.ds.advance_mapping_1.controller;

import org.ds.advance_mapping_1.dto.CourseDTO;
import org.ds.advance_mapping_1.dto.InstructorDTO;
import org.ds.advance_mapping_1.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/instructors")
public class InstructorController {
    
    private final InstructorService instructorService;

    @Autowired
    public InstructorController(InstructorService instructorService){
        this.instructorService = instructorService;
    }

    @PostMapping
    public InstructorDTO addInstructor(@RequestBody InstructorDTO instructorDTO) {
        return instructorService.addInstructor(instructorDTO);
    }

    @GetMapping("/{instructorId}")
    public InstructorDTO getInstructorById(@PathVariable long instructorId){
        return instructorService.findByInstructorId(instructorId);
    }

    @GetMapping
    public List<InstructorDTO> getAllInstructor(){
        return instructorService.findByAllInstructor();
    }

    @PutMapping
    public InstructorDTO updateInstructor(@RequestBody InstructorDTO instructorDTO) {
        return instructorService.updateInstructor(instructorDTO);
    }

    @DeleteMapping("/{instructorId}")
    public InstructorDTO deleteInstructorById(@PathVariable long instructorId){
        return instructorService.deleteByInstructorId(instructorId);
    }

    @GetMapping("/details/{instructorDetailsId}")
    public InstructorDTO getByInstructorDetailsId(@PathVariable long instructorDetailsId){
        return instructorService.findByInstructorDetailsId(instructorDetailsId);
    }

    @DeleteMapping("/details/{instructorDetailsId}")
    public InstructorDTO deleteByInstructorDetailsId(@PathVariable long instructorDetailsId){
        return instructorService.deleteByInstructorDetailsId(instructorDetailsId);
    }


    @PostMapping("/courses/{instructorId}")
    public InstructorDTO addCourses(@PathVariable long instructorId, @RequestBody List<CourseDTO> courseDTOList) {
        return instructorService.addCoursesToInstructor(instructorId, courseDTOList);
    }
}

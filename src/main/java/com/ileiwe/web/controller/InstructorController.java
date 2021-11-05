package com.ileiwe.web.controller;

import com.ileiwe.data.dto.CourseDto;
import com.ileiwe.data.dto.InstructorPartyDto;
import com.ileiwe.data.model.Course;
import com.ileiwe.data.model.Instructor;
import com.ileiwe.service.courseService.CourseService;
import com.ileiwe.service.instructorService.InstructorService;
import com.ileiwe.service.instructorService.InstructorServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/instructor")
public class InstructorController implements Controllable{
    @Autowired
    InstructorService instructorServiceImpl;
     @PostMapping("/{instructorId}")
    public Course createCourse(@PathVariable Long instructorId, @RequestBody Course course){
        return instructorServiceImpl.CreateCurse(instructorId, course);
    }

    @PatchMapping("/{instructorId}/{courseId}")
    public Course updateCourse(@PathVariable Long instructorId, @PathVariable Long courseId, @RequestBody CourseDto courseDto) {
        return instructorServiceImpl.updateCourse(instructorId, courseId, courseDto);
    }

    @Override
    public Instructor save(InstructorPartyDto dto) {
        return null;
    }

    @PutMapping("/{instructorId}/{courseId}")
    public Course publishCourse(@PathVariable Long instructorId, @PathVariable Long courseId) {
         return instructorServiceImpl.publishCourse(instructorId, courseId);

    }

    @Override
    @DeleteMapping("/{instructorId}/{courseId}")
    public void deleteCourseById(@PathVariable Long instructorId, @PathVariable Long courseId) {
            instructorServiceImpl.deleteCourseById(instructorId, courseId);
            log.info("Course with id "+courseId +" has been deleted");
    }

    @GetMapping("/{instructorId}")
    public List<Course> viewCourses(@PathVariable Long instructorId) {
        return instructorServiceImpl.viewCourses(instructorId);
    }

    @GetMapping("/get/{instructorId}")
    public List<Course> viewPublishedCourses(@PathVariable Long instructorId) {
        return instructorServiceImpl.viewPublishedCourses(instructorId);
    }

    @Override
    @GetMapping("/get/{instructorId}/{courseId}")
    public Course viewCourseById(@PathVariable Long instructorId, @PathVariable Long courseId) {
        return instructorServiceImpl.viewCourseById(instructorId,courseId);
    }

    @Override
    @GetMapping("/{instructorId}/{title}")
    public Course viewCourseByTitle(@PathVariable Long instructorId, @PathVariable String title) {
        return instructorServiceImpl.viewCourseByTitle(instructorId, title);
    }

}

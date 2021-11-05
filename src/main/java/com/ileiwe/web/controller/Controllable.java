package com.ileiwe.web.controller;

import com.ileiwe.data.dto.CourseDto;
import com.ileiwe.data.dto.InstructorPartyDto;
import com.ileiwe.data.model.Course;
import com.ileiwe.data.model.Instructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public interface Controllable {
    Instructor save(InstructorPartyDto dto);
    Course createCourse(@PathVariable Long id, @RequestBody Course course);
    Course updateCourse(@PathVariable Long id, Long courseId, @RequestBody CourseDto courseDto);
    Course publishCourse(@PathVariable Long instructorId, Long courseId);
    void deleteCourseById(@PathVariable Long InstructorId, Long courseId);
    List<Course>viewCourses(@PathVariable Long id);
    List<Course> viewPublishedCourses(@PathVariable Long instructorId);
    Course viewCourseById(@PathVariable Long id, Long courseId);
    Course viewCourseByTitle(@PathVariable Long instructorId, String title);
}

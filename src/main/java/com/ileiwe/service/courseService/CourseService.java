package com.ileiwe.service.courseService;

import com.ileiwe.data.dto.CourseDto;
import com.ileiwe.data.model.Course;

import java.util.List;

public interface CourseService{
    Course createCourse(Course course);
    Course update(Long id, CourseDto courseDto);
    void delete(Long id);
    List<Course> viewCourses();
    Course publishCourse(Long courseId);
    Course findCourseByTitle(String title);
    Course findById(Long id);
    List<Course>showPublishedCourses();
}

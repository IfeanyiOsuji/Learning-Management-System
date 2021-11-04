package com.ileiwe.service.courseService;

import com.ileiwe.data.dto.CourseDto;
import com.ileiwe.data.model.Course;

public interface CourseService{
    Course create(Course course);
    Long update(Long id, CourseDto courseDto);
    void delete(Long id);
    Course viewCourse(Long id);
    void publishCourse(Long id);
    Course findCourseByTitle(String title);
}

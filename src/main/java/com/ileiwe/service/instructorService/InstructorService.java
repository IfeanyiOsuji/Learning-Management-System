package com.ileiwe.service.instructorService;

import com.ileiwe.data.dto.CourseDto;
import com.ileiwe.data.dto.InstructorPartyDto;
import com.ileiwe.data.model.Course;
import com.ileiwe.data.model.Instructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface InstructorService {
    Instructor save(InstructorPartyDto dto);
    Course CreateCurse(Long id, Course course);
    Course updateCourse(Long id, Long courseId, CourseDto courseDto);
    Course publishCourse(Long instructorId, Long courseId);
    void deleteCourseById(Long InstructorId, Long courseId);
    List<Course>viewCourses(Long id);
    List<Course> viewPublishedCourses(Long instructorId);
    Course viewCourseById(Long id, Long courseId);
    Course viewCourseByTitle(Long instructorId, String title);
}

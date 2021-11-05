package com.ileiwe.service.courseService;

import com.ileiwe.data.model.Course;
import com.ileiwe.data.repository.CourseRepository;
import com.ileiwe.data.repository.InstructorRepository;
import com.ileiwe.web.exceptions.CourseAlreadyExistException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Slf4j
class CourseServiceImplTest {
    @Autowired
InstructorRepository instructorRepository;
    @Autowired
    CourseRepository courseRepository;

    @Autowired
    CourseServiceImpl courseServiceImpl;
    @Test
    void create() {
        Course course=new Course();
        course.setTitle("Java");
        course.setDescription("Learning programming using the language of java");
        course.setDuration("1hour, 30min");
        course.setLanguage("English");
       assertThat(courseRepository.findAll().size()).isEqualTo(0);
       courseServiceImpl.createCourse(course);
        assertThat(courseRepository.findAll().size()).isEqualTo(1);
    }
    @Test
    void createCourseWithAlreadyExistingTitle(){
        Course course=new Course();
        course.setTitle("Python");
        course.setDescription("Learning programming using the language of python");
        course.setDuration("1hour, 30min");
        course.setLanguage("English");
        assertThat(courseRepository.findAll().size()).isEqualTo(1);
        courseServiceImpl.createCourse(course);
        assertThrows(CourseAlreadyExistException.class, ()->courseServiceImpl.createCourse(course));

    }

    @Test
    void updateCourse() {
    }

    @Test
    void testUpdate() {
    }

    @Test
    void delete() {
        int size = courseRepository.findAll().size();
        courseServiceImpl.delete(1L);
        assertThat(courseRepository.findAll().size()).isEqualTo(size-1);

    }

    @Test
    void viewCourse() {
    }

    @Test
    void publishCourse() {
    }
}
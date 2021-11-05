package com.ileiwe.service.courseService;

import com.ileiwe.data.dto.CourseDto;
import com.ileiwe.data.model.Course;
import com.ileiwe.data.model.Instructor;
import com.ileiwe.data.repository.InstructorRepository;
import com.ileiwe.service.instructorService.InstructorServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SpringBootTest
@Slf4j
class InstructorServiceImplTest {
    @Autowired
    InstructorServiceImpl instructorServiceImpl;
    @Autowired
    CourseServiceImpl courseServiceImpl;
    @Autowired
    InstructorRepository instructorRepository;
    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }


    @Test
    @Transactional
    @Rollback(value = false)
    void createCurse() {
       Optional<Instructor> instructor = instructorRepository.findById(2L);

           Course course = new Course();
           course.setTitle("CSS");
           course.setDescription("Demystifying programming using CSS");
           course.setDuration("2 hours");
           course.setLanguage("French");
           log.info("Course before saving {}", course);
           course.setInstructor(instructor.get());
           instructorServiceImpl.CreateCurse(instructor.get().getId(), course);
           log.info("course after saving {}", course);

    }
    @Test
    void updateCourse(){
        CourseDto courseDto = new CourseDto();
        courseDto.setLanguage("Portuguese");
        courseDto.setDuration("3 hours");
        courseDto.setTitle("Java Collections For Beginners");
        Course course = courseServiceImpl.findById(1L);
        log.info("Before updating course {}", course.getTitle());
        Course updated =instructorServiceImpl.updateCourse(1L, course.getId(), courseDto);
        assertThat(courseDto.getTitle()).isEqualTo(updated.getTitle());
        log.info("After updating course {}", updated.getTitle());
    }
    @Test
    void publishCourse(){
        Course unpublishedCourse = courseServiceImpl.findById(2L);
        log.info("Course published date before publishing {}", unpublishedCourse.getDatePublished());
        log.info("Course published status before publishing {}", unpublishedCourse.isPublished());
        Course course = instructorServiceImpl.publishCourse( 1l,unpublishedCourse.getId());
        assertThat(course.isPublished()).isEqualTo(true);
        assertThat(course.getDatePublished()).isNotNull();
        log.info("Course published date after publishing {}", course.getDatePublished());
        log.info("Course published status after publishing {}", course.isPublished());

    }
    @Test
    void deleteCourseById(){
        Course unpublishedCourse = courseServiceImpl.findById(5L);
        int size = instructorServiceImpl.viewCourses(1L).size();
        log.info("List of courses before deleting {}", size);
        instructorServiceImpl.deleteCourseById(2L,unpublishedCourse.getId());
        assertThat(instructorServiceImpl.viewCourses(2L).size()).isEqualTo(size - 1);
        log.info("List of courses after deleting {}", instructorServiceImpl.viewCourses(2L).size());

    }
    @Test
    @Transactional
    @Rollback(value = false)
    void viewCourses(){
        List<Course> courses = instructorServiceImpl.viewCourses(2L);
        log.info("Courses in the list {}", courses.toString());
    }
    @Test
    @Transactional
    @Rollback(value = false)
    void viewPublishedCourses(){
        List<Course> courses = instructorServiceImpl.viewPublishedCourses(2L);
        log.info("Courses in the list {}", courses.toString());
    }
    @Test
    @Transactional
    @Rollback(value = false)
    void getCourseById(){
        Course course = instructorServiceImpl.viewCourseById(2L, 1L);
        log.info("Course with id "+course.getId() +" is "+course);
    }
    @Test
    @Transactional
    @Rollback(value = false)
    void getCourseByTitle(){
        Course course = instructorServiceImpl.viewCourseByTitle(2L, "CSS");
        log.info("Course with title "+course.getTitle() +" is "+course);
    }
}
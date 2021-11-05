package com.ileiwe.service.courseService;

import com.ileiwe.data.dto.CourseDto;
import com.ileiwe.data.model.Course;
import com.ileiwe.data.model.Instructor;
import com.ileiwe.data.repository.CourseRepository;
import com.ileiwe.data.repository.InstructorRepository;
import com.ileiwe.service.mapper.CourseMapper;
import com.ileiwe.web.exceptions.CourseAlreadyExistException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@Slf4j
@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    InstructorRepository instructorRepository;

    @Autowired
    CourseRepository courseRepository;
    @Autowired
    CourseMapper courseMapper;

    @Override
    @Transactional
    public Course createCourse(Course course) {
        Course courseInRepo = courseRepository.findCourseByTitle(course.getTitle());
        if (courseInRepo != null) {
            throw new CourseAlreadyExistException("Course with tile " + course.getTitle() + " already exists");
        }
        Course createdCourse = courseRepository.save(course);
        return createdCourse;

    }

    @Override
    public Course update(Long id, CourseDto courseDto) {
        if (courseDto == null) {
            log.info("course dto {}", courseDto);
            throw new NullPointerException("Course cannot be null");
        }
        Optional<Course> courseInRepo = courseRepository.findById(id);
        if (courseInRepo.isPresent()) {
            Course course = courseInRepo.get();
            courseMapper.mapToCourse(courseDto, course);
            Course updated = courseRepository.save(course);
            return updated;
        } else throw new NullPointerException("Course with id " + id + " does not exist");

    }

    @Override
    public void delete(Long id) {
        Optional<Course> course = courseRepository.findById(id);
        course.ifPresent(value -> courseRepository.delete(value));
        if(!course.isPresent())
        throw new NullPointerException("Course with id " + id + " not found");

    }

    @Override
    public List<Course> viewCourses() {
        List<Course> courses = courseRepository.findAll();
        if (courses.size() == 0) {
            throw new NullPointerException("No course found");
        }
        return courses;
}


    @Override
    public Course publishCourse(Long id) {
        Optional<Course>course = courseRepository.findById(id);
         if(course.isPresent()){
            CourseDto courseDto = new CourseDto();
            courseDto.setPublished(true);
            courseDto.setDatePublished(LocalDateTime.now());
        log.info("Course dto {}", courseDto);
        return update(course.get().getId(), courseDto);
        }
        throw new NullPointerException("Course with the id "+id +" does not exist");

    }

    @Override
    public Course findCourseByTitle(String title) {
        List<Course> courses = courseRepository.findAll();
        for(Course course : courses){
            if(course.getTitle().equals(title)){
                return course;
            }
        }
        return null;
    }

    @Override
    public Course findById(Long id) {
        Optional<Course>course = courseRepository.findById(id);
        if(course.isPresent()){
            return course.get();
        }
        throw new NullPointerException("Course with id "+id +" does not exist");
    }

    @Override
    public List<Course> showPublishedCourses() {
        List<Course> courses = courseRepository.findAll();
        if (courses.size() == 0) {
            throw new NullPointerException("No course found");
        }
        courses.removeIf(course -> !course.isPublished());
        return courses;
    }
}

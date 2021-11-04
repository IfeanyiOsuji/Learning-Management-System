package com.ileiwe.service.courseService;

import com.ileiwe.data.dto.CourseDto;
import com.ileiwe.data.model.Course;
import com.ileiwe.data.repository.CourseRepository;
import com.ileiwe.data.repository.InstructorRepository;
import com.ileiwe.service.CourseService;
import com.ileiwe.service.mapper.CourseMapper;
import com.ileiwe.web.exceptions.CourseAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

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
    public Course create(Course course) {
        Course courseInRepo = courseRepository.findCourseByTitle(course.getTitle());
     if(courseInRepo != null){
          throw new CourseAlreadyExistException("Course with tile "+course.getTitle() +" already exists");
      }
        Course createdCourse =courseRepository.save(course);
        return createdCourse;

    }

    @Override
    public Long update(Long id, CourseDto courseDto) {
        if(courseDto == null){
            throw new NullPointerException("Course cannot be null");
        }
        Optional<Course> courseInRepo = courseRepository.findById(id);
        if(courseInRepo.isPresent()){
            Course course = courseInRepo.get()
            courseMapper.mapToCourse(courseDto, course);
            courseRepository.save(course);
            return id;
        }
        else throw new NullPointerException("Course with id "+id +" does not exist");

    }

    @Override
    public void delete(Long id) {
        Optional<Course> course=courseRepository.findById(id);
        course.ifPresent(value -> courseRepository.delete(value));
        throw new NullPointerException("Course with id "+id +" not found");

    }

    @Override
    public Course viewCourse(Long id) {

        return courseRepository.findById(id).orElse(null);
    }

    @Override
    public void publishCourse(Long id) {

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
}

package com.ileiwe.service.instructorService;

import com.ileiwe.data.dto.CourseDto;
import com.ileiwe.data.dto.InstructorPartyDto;
import com.ileiwe.data.model.*;
import com.ileiwe.data.repository.InstructorRepository;
import com.ileiwe.service.courseService.CourseServiceImpl;
import com.ileiwe.service.mapper.CourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author oluwatobi
 * @version 1.0
 * @date on 29/10/2021
 * inside the package - com.ileiwe.service.instructor
 */

@Service
public class InstructorServiceImpl implements InstructorService{

    @Autowired
    InstructorRepository instructorRepository;

    @Autowired
    CourseServiceImpl courseServiceImpl;
    @Autowired
    CourseMapper courseMapper;

    InstructorPartyDto instructorPartyDto;


    @Override
    public Instructor save(InstructorPartyDto instructorDto) {


        if(instructorDto == null){
            throw new IllegalArgumentException("Instructor cannot be null");
        }
        LearningParty learningParty
                = new LearningParty(instructorDto.getEmail()
//                            ,passwordEncoder.encode(instructorDto.getPassword())
                ,instructorDto.getPassword()
                        , new Authority(Role.ROLE_INSTRUCTOR));

        Instructor instructor = Instructor.builder()
                .lastname(instructorDto.getLastname())
                .firstname(instructorDto.getFirstname())
                .learningParty(learningParty).build();

       return instructorRepository.save(instructor);
    }
    @Override
    public Course CreateCurse(Long instructorId, Course course) {
        Optional<Instructor>instructor = instructorRepository.findById(instructorId);
        if(instructor.isPresent()){
            course.setInstructor(instructor.get());
            return courseServiceImpl.createCourse(course);
        }

        throw new NullPointerException("Instructor with id "+instructorId +" does not exist");
    }

    @Override
    public Course updateCourse(Long instructorId, Long courseId, CourseDto course) {
        if(isInstructor(instructorId)){
            return courseServiceImpl.update(courseId,course);
        }
        throw new NullPointerException("Instructor with id "+instructorId +" does not exist");

    }

    @Override
    public Course publishCourse(Long instructorId, Long courseId) {
        if(isInstructor(instructorId)){
           Course course = courseServiceImpl.publishCourse(courseId);
           return course;
        }
        throw new NullPointerException("Instructor with id "+instructorId +" does not exist");
    }

    @Override
    public void deleteCourseById(Long instructorId, Long courseId) {
        if(isInstructor(instructorId)){
            courseServiceImpl.delete(courseId);
        }
        else
        throw new NullPointerException("Instructor with id "+instructorId +" does not exist");
    }

    @Override
    public List<Course> viewCourses(Long instructorId) {
        if(isInstructor(instructorId)){
            return courseServiceImpl.viewCourses();
        }
        throw new NullPointerException("Instructor with id "+instructorId +" does not exist");
    }

    @Override
    public List<Course> viewPublishedCourses(Long instructorId) {
        if(isInstructor(instructorId)){
            return courseServiceImpl.showPublishedCourses();
        }
        throw new NullPointerException("Instructor with id "+instructorId +" does not exist");
    }

    @Override
    public Course viewCourseById(Long instructorId, Long courseId) {
        if(isInstructor(instructorId)){
            return courseServiceImpl.findById(courseId);
        }
        throw new NullPointerException("Instructor with id "+instructorId +" does not exist");
    }

    @Override
    public Course viewCourseByTitle(Long instructorId, String title) {
        if(isInstructor(instructorId)){
            return courseServiceImpl.findCourseByTitle(title);
        }
        throw new NullPointerException("Instructor with id "+instructorId +" does not exist");
    }

    private boolean isInstructor(Long instructorId){
        Optional<Instructor>instructor = instructorRepository.findById(instructorId);
        if(instructor.isPresent()){
            return true;
        }
        return false;
    }


}

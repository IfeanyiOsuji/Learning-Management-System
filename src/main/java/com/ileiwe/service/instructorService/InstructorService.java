package com.ileiwe.service.courseService;

import com.ileiwe.data.model.Course;
import org.springframework.stereotype.Service;

@Service
public interface InstructorService {
    Long CreateCurse(Long id, Course course);
    Long updateCourse(Long id, Long courseId, Course course);
}

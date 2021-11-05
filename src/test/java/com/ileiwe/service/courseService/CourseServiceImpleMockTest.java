package com.ileiwe.service.courseService;


import com.ileiwe.data.dto.CourseDto;
import com.ileiwe.data.model.Course;
import com.ileiwe.data.repository.CourseRepository;
import com.ileiwe.service.mapper.CourseMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.*;
@Slf4j
public class CourseServiceImpleMockTest {
    @Mock
    CourseRepository courseRepository;
    @InjectMocks
    CourseServiceImpl courseServiceImpl;
    @Autowired
    CourseMapper mapper;

    @BeforeEach
    void setUp() {
        courseServiceImpl = new CourseServiceImpl();
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void updateCourse(){
//        CourseDto courseDto = new CourseDto();
//        courseDto.setLanguage("Portuguese");
//        courseDto.setDuration("3 hours");
//        courseDto.setTitle("Java Collections");
//        Course courseToUpdate = courseRepository.findCourseByTitle("Java");
//        log.info("Before updating course {}", courseToUpdate);
//        when(courseServiceImpl.update(courseToUpdate.getId(), courseDto)).thenReturn(courseToUpdate.getId());
//        courseServiceImpl.update(courseToUpdate.getId(), courseDto);
//        verify(courseRepository, times(1));

    }
}

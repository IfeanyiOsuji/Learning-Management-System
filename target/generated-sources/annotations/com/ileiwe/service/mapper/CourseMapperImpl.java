package com.ileiwe.service.mapper;

import com.ileiwe.data.dto.CourseDto;
import com.ileiwe.data.model.Course;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-11-05T11:54:03+0100",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 16.0.2 (Oracle Corporation)"
)
@Component
public class CourseMapperImpl implements CourseMapper {

    @Override
    public void mapToCourse(CourseDto courseDto, Course course) {
        if ( courseDto == null ) {
            return;
        }

        if ( courseDto.getTitle() != null ) {
            course.setTitle( courseDto.getTitle() );
        }
        if ( courseDto.getDescription() != null ) {
            course.setDescription( courseDto.getDescription() );
        }
        if ( courseDto.getDuration() != null ) {
            course.setDuration( courseDto.getDuration() );
        }
        if ( courseDto.getLanguage() != null ) {
            course.setLanguage( courseDto.getLanguage() );
        }
        if ( courseDto.getDatePublished() != null ) {
            course.setDatePublished( courseDto.getDatePublished() );
        }
        course.setPublished( courseDto.isPublished() );
        if ( courseDto.getInstructor() != null ) {
            course.setInstructor( courseDto.getInstructor() );
        }
    }
}

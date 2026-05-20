package project.ptit_cntt1_it211_session06.mapper;

import project.ptit_cntt1_it211_session06.dto.request.CourseRequestDTO;
import project.ptit_cntt1_it211_session06.dto.response.CourseResponseDTO;
import project.ptit_cntt1_it211_session06.entity.Course;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper {

    public Course toEntity(CourseRequestDTO dto) {

        Course course = new Course();

        course.setName(dto.getName());
        course.setDescription(dto.getDescription());
        course.setPrice(dto.getPrice());

        return course;
    }

    public CourseResponseDTO toResponseDTO(Course course) {

        CourseResponseDTO dto = new CourseResponseDTO();

        dto.setId(course.getId());
        dto.setName(course.getName());
        dto.setDescription(course.getDescription());
        dto.setPrice(course.getPrice());
        dto.setImageUrl(course.getImageUrl());

        return dto;
    }

    public void updateEntityFromDTO(CourseRequestDTO dto, Course course) {

        course.setName(dto.getName());
        course.setDescription(dto.getDescription());
        course.setPrice(dto.getPrice());
    }
}

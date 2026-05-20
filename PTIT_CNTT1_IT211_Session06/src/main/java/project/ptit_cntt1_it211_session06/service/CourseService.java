package project.ptit_cntt1_it211_session06.service;

import project.ptit_cntt1_it211_session06.dto.request.CourseRequestDTO;
import project.ptit_cntt1_it211_session06.dto.response.CourseResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface CourseService {

    Page<CourseResponseDTO> getAllCourses(
            int page,
            int size,
            String sort
    );

    CourseResponseDTO getCourseById(Long id);

    CourseResponseDTO createCourse(CourseRequestDTO requestDTO);

    CourseResponseDTO updateCourse(
            Long id,
            CourseRequestDTO requestDTO
    );

    CourseResponseDTO patchCourse(
            Long id,
            Map<String, Object> updates
    );

    void deleteCourse(Long id);

    CourseResponseDTO uploadCourseImage(
            Long id,
            MultipartFile file
    );

    void deleteCourseImage(Long id);
}
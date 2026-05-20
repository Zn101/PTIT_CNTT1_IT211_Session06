package project.ptit_cntt1_it211_session06.service.impl;

import project.ptit_cntt1_it211_session06.dto.request.CourseRequestDTO;
import project.ptit_cntt1_it211_session06.dto.response.CourseResponseDTO;
import project.ptit_cntt1_it211_session06.entity.Course;
import project.ptit_cntt1_it211_session06.exception.ResourceNotFoundException;
import project.ptit_cntt1_it211_session06.mapper.CourseMapper;
import project.ptit_cntt1_it211_session06.repository.CourseRepository;
import project.ptit_cntt1_it211_session06.service.CourseService;
import project.ptit_cntt1_it211_session06.service.FileStorageService;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;
    private final FileStorageService fileStorageService;

    public CourseServiceImpl(
            CourseRepository courseRepository,
            CourseMapper courseMapper,
            FileStorageService fileStorageService
    ) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
        this.fileStorageService = fileStorageService;
    }

    @Override
    public Page<CourseResponseDTO> getAllCourses(
            int page,
            int size,
            String sort
    ) {

        String[] sortParams = sort.split(",");

        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by(
                        Sort.Direction.fromString(sortParams[1]),
                        sortParams[0]
                )
        );

        return courseRepository
                .findAll(pageable)
                .map(courseMapper::toResponseDTO);
    }

    @Override
    public CourseResponseDTO getCourseById(Long id) {

        Course course = courseRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Course not found with id: " + id
                        )
                );

        return courseMapper.toResponseDTO(course);
    }

    @Override
    public CourseResponseDTO createCourse(CourseRequestDTO requestDTO) {

        Course course = courseMapper.toEntity(requestDTO);

        Course savedCourse = courseRepository.save(course);

        return courseMapper.toResponseDTO(savedCourse);
    }

    @Override
    public CourseResponseDTO updateCourse(
            Long id,
            CourseRequestDTO requestDTO
    ) {

        Course existingCourse = courseRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Course not found with id: " + id
                        )
                );

        courseMapper.updateEntityFromDTO(requestDTO, existingCourse);

        Course updatedCourse = courseRepository.save(existingCourse);

        return courseMapper.toResponseDTO(updatedCourse);
    }

    @Override
    public CourseResponseDTO patchCourse(
            Long id,
            Map<String, Object> updates
    ) {

        Course course = courseRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Course not found with id: " + id
                        )
                );

        if (updates.containsKey("name")) {
            course.setName((String) updates.get("name"));
        }

        if (updates.containsKey("description")) {
            course.setDescription((String) updates.get("description"));
        }

        if (updates.containsKey("price")) {
            course.setPrice(
                    Double.valueOf(updates.get("price").toString())
            );
        }

        Course updatedCourse = courseRepository.save(course);

        return courseMapper.toResponseDTO(updatedCourse);
    }

    @Override
    public void deleteCourse(Long id) {

        Course course = courseRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Course not found with id: " + id
                        )
                );

        if (course.getImageUrl() != null) {
            fileStorageService.deleteFile(course.getImageUrl());
        }

        courseRepository.delete(course);
    }

    @Override
    public CourseResponseDTO uploadCourseImage(
            Long id,
            MultipartFile file
    ) {

        return null;
    }

    @Override
    public void deleteCourseImage(Long id) {

    }
}

package project.ptit_cntt1_it211_session06.controller;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import project.ptit_cntt1_it211_session06.dto.request.CourseRequestDTO;
import project.ptit_cntt1_it211_session06.dto.response.CourseResponseDTO;
import project.ptit_cntt1_it211_session06.service.CourseService;

import java.util.Map;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public Page<CourseResponseDTO> getAllCourses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id,asc") String sort
    ) {
        return courseService.getAllCourses(page, size, sort);
    }

    @GetMapping("/{id}")
    public CourseResponseDTO getCourseById(@PathVariable Long id) {
        return courseService.getCourseById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CourseResponseDTO createCourse(@Valid @RequestBody CourseRequestDTO requestDTO) {
        return courseService.createCourse(requestDTO);
    }

    @PutMapping("/{id}")
    public CourseResponseDTO updateCourse(
            @PathVariable Long id,
            @Valid @RequestBody CourseRequestDTO requestDTO
    ) {
        return courseService.updateCourse(id, requestDTO);
    }

    @PatchMapping("/{id}")
    public CourseResponseDTO patchCourse(
            @PathVariable Long id,
            @RequestBody Map<String, Object> updates
    ) {
        return courseService.patchCourse(id, updates);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
    }

    @PostMapping("/{id}/image")
    public CourseResponseDTO uploadCourseImage(
            @PathVariable Long id,
            @RequestParam("file") MultipartFile file
    ) {
        return courseService.uploadCourseImage(id, file);
    }

    @DeleteMapping("/{id}/image")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCourseImage(@PathVariable Long id) {
        courseService.deleteCourseImage(id);
    }
}

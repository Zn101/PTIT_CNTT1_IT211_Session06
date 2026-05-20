package project.ptit_cntt1_it211_session06.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.ptit_cntt1_it211_session06.dto.response.CourseResponseDTO;
import project.ptit_cntt1_it211_session06.service.CourseService;

@RestController
@RequestMapping("/api/courses")
public class CourseController {
    private final CourseService courseService;
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity<Page<CourseResponseDTO>> getAllCourses(
            @PageableDefault(
                    page = 0,
                    size = 10,
                    sort = {"id"},
                    direction = Sort.Direction.ASC
            ) Pageable pageable
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(courseService.getAllCourses(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCourseById(
            @PathVariable Long id
    ) {
        try {
            return ResponseEntity.ok(courseService.getCourseById(id));
        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }
}

package project.ptit_cntt1_it211_session06.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.ptit_cntt1_it211_session06.dto.request.CourseRequestDTO;
import project.ptit_cntt1_it211_session06.dto.response.CourseResponseDTO;
import project.ptit_cntt1_it211_session06.service.CourseService;

import java.util.Map;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PatchMapping("/{id}")
    public ResponseEntity<CourseResponseDTO> patchCourse(
            @PathVariable Long id,
            @RequestBody Map<String, Object> updates
    ) {
        CourseResponseDTO updatedCourse = courseService.patchCourse(id, updates);
        return ResponseEntity.ok(updatedCourse);
    }
}

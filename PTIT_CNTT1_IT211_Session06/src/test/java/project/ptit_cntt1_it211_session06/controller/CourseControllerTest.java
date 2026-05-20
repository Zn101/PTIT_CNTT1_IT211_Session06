package project.ptit_cntt1_it211_session06.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import project.ptit_cntt1_it211_session06.dto.request.CourseRequestDTO;
import project.ptit_cntt1_it211_session06.entity.Course;
import project.ptit_cntt1_it211_session06.repository.CourseRepository;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CourseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CourseRepository courseRepository;

    private Course testCourse;

    @BeforeEach
    void setUp() {
        courseRepository.deleteAll();
        
        testCourse = new Course();
        testCourse.setName("Test Course");
        testCourse.setDescription("Test Description");
        testCourse.setPrice(100.0);
        testCourse.setImageUrl(null);
        testCourse = courseRepository.save(testCourse);
    }

    @Test
    void patchCourse_UpdatePrice_Success() throws Exception {
        Map<String, Object> updates = new HashMap<>();
        updates.put("price", 199.99);

        mockMvc.perform(patch("/api/courses/{id}", testCourse.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updates)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(testCourse.getId()))
                .andExpect(jsonPath("$.name").value("Test Course"))
                .andExpect(jsonPath("$.price").value(199.99));
    }

    @Test
    void patchCourse_UpdateName_Success() throws Exception {
        Map<String, Object> updates = new HashMap<>();
        updates.put("name", "Updated Course Name");

        mockMvc.perform(patch("/api/courses/{id}", testCourse.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updates)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(testCourse.getId()))
                .andExpect(jsonPath("$.name").value("Updated Course Name"))
                .andExpect(jsonPath("$.price").value(100.0));
    }

    @Test
    void patchCourse_UpdateDescription_Success() throws Exception {
        Map<String, Object> updates = new HashMap<>();
        updates.put("description", "Updated Description");

        mockMvc.perform(patch("/api/courses/{id}", testCourse.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updates)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(testCourse.getId()))
                .andExpect(jsonPath("$.description").value("Updated Description"));
    }

    @Test
    void patchCourse_UpdateMultipleFields_Success() throws Exception {
        Map<String, Object> updates = new HashMap<>();
        updates.put("name", "Updated Name");
        updates.put("price", 299.99);
        updates.put("description", "Updated Description");

        mockMvc.perform(patch("/api/courses/{id}", testCourse.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updates)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(testCourse.getId()))
                .andExpect(jsonPath("$.name").value("Updated Name"))
                .andExpect(jsonPath("$.price").value(299.99))
                .andExpect(jsonPath("$.description").value("Updated Description"));
    }

    @Test
    void patchCourse_InvalidId_Failure() throws Exception {
        Long nonExistentId = 99999L;
        
        Map<String, Object> updates = new HashMap<>();
        updates.put("name", "Updated Name");

        mockMvc.perform(patch("/api/courses/{id}", nonExistentId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updates)))
                .andExpect(status().isNotFound());
    }
}

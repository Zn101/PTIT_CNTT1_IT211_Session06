package project.ptit_cntt1_it211_session06.repository;

import project.ptit_cntt1_it211_session06.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
}
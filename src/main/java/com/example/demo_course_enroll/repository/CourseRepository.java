package com.example.demo_course_enroll.repository;

import com.example.demo_course_enroll.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findByDeletedFalse();

    Optional<Course> findByIdAndDeletedFalse(Long id);

    @Query("SELECT DISTINCT c FROM Course c LEFT JOIN FETCH c.teacher WHERE c.deleted = false")
    List<Course> findAllActiveWithTeacher();

    @Query("SELECT c FROM Course c LEFT JOIN FETCH c.teacher WHERE c.id = :id AND c.deleted = false")
    Optional<Course> findActiveByIdWithTeacher(Long id);

    @Query("SELECT DISTINCT c FROM Course c LEFT JOIN FETCH c.teacher WHERE c.teacher.id = :teacherId AND c.deleted = false")
    List<Course> findByTeacherIdAndDeletedFalse(Long teacherId);

    @Query("SELECT DISTINCT c FROM Course c LEFT JOIN FETCH c.teacher JOIN c.students s WHERE s.id = :studentId AND c.deleted = false AND s.deleted = false")
    List<Course> findByStudentIdAndDeletedFalse(Long studentId);
}

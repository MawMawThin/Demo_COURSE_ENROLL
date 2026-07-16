package com.example.demo_course_enroll.repository;

import com.example.demo_course_enroll.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    List<Teacher> findByDeletedFalse();

    Optional<Teacher> findByIdAndDeletedFalse(Long id);
}

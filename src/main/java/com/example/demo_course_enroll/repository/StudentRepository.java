package com.example.demo_course_enroll.repository;

import com.example.demo_course_enroll.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Long> {
}

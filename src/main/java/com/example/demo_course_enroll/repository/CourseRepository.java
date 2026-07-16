package com.example.demo_course_enroll.repository;

import com.example.demo_course_enroll.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course,Long> {
}

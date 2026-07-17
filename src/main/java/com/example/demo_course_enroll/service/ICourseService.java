package com.example.demo_course_enroll.service;

import com.example.demo_course_enroll.payload.input.CourseInput;
import com.example.demo_course_enroll.payload.output.CourseOutput;

import java.util.List;

public interface ICourseService {

    String registerCourse(CourseInput input);

    String updateCourse(Long id, CourseInput input);

    String deleteCourse(Long id);

    List<CourseOutput> getAllCourses();

    CourseOutput getCourseById(Long id);

    List<CourseOutput> getCoursesByTeacher(Long teacherId);
}

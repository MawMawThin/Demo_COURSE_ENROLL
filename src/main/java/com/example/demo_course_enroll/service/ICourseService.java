package com.example.demo_course_enroll.service;

import com.example.demo_course_enroll.payload.input.CourseInput;
import com.example.demo_course_enroll.payload.output.CourseOutput;

import java.util.List;

public interface ICourseService {

    CourseOutput registerCourse(CourseInput input);

    CourseOutput updateCourse(Long id, CourseInput input);

    void deleteCourse(Long id);

    List<CourseOutput> getAllCourses();

    CourseOutput getCourseById(Long id);

    List<CourseOutput> getCoursesByTeacher(Long teacherId);
}

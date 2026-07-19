package com.example.demo_course_enroll.service;

import com.example.demo_course_enroll.payload.input.StudentInput;
import com.example.demo_course_enroll.payload.output.CourseOutput;
import com.example.demo_course_enroll.payload.output.StudentOutput;

import java.util.List;

public interface IStudentService {

    StudentOutput registerStudent(StudentInput input);

    StudentOutput updateStudent(Long id, StudentInput input);

    void deleteStudent(Long id);

    List<StudentOutput> getAllStudents();

    StudentOutput getStudentById(Long id);

    CourseOutput enrollCourse(Long studentId, Long courseId);

    void unenrollCourse(Long studentId, Long courseId);

    List<CourseOutput> getCoursesByStudent(Long studentId);
}

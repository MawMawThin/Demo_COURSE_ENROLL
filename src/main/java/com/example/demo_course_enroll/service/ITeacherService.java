package com.example.demo_course_enroll.service;

import com.example.demo_course_enroll.payload.input.TeacherInput;
import com.example.demo_course_enroll.payload.output.TeacherOutput;

import java.util.List;

public interface ITeacherService {

    String registerTeacher(TeacherInput input);

    String updateTeacher(Long id, TeacherInput input);

    String deleteTeacher(Long id);

    List<TeacherOutput> getAllTeachers();

    TeacherOutput getTeacherById(Long id);
}

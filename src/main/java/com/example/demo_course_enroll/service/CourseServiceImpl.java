package com.example.demo_course_enroll.service;

import com.example.demo_course_enroll.exception.ResourceNotFoundException;
import com.example.demo_course_enroll.model.Course;
import com.example.demo_course_enroll.model.Teacher;
import com.example.demo_course_enroll.payload.input.CourseInput;
import com.example.demo_course_enroll.payload.output.CourseOutput;
import com.example.demo_course_enroll.repository.CourseRepository;
import com.example.demo_course_enroll.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements ICourseService {

    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;

    @Override
    @Transactional
    public CourseOutput registerCourse(CourseInput input) {
        Course course = CourseInput.toModel(input);

        if (input.getTeacherId() != null) {
            Teacher teacher = teacherRepository.findByIdAndDeletedFalse(input.getTeacherId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Teacher not found with id: " + input.getTeacherId()));
            course.setTeacher(teacher);
        }

        return CourseOutput.fromModel(courseRepository.save(course));
    }

    @Override
    @Transactional
    public CourseOutput updateCourse(Long id, CourseInput input) {
        Course course = courseRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + id));

        CourseInput.updateModel(input, course);

        if (input.getTeacherId() != null) {
            Teacher teacher = teacherRepository.findByIdAndDeletedFalse(input.getTeacherId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Teacher not found with id: " + input.getTeacherId()));
            course.setTeacher(teacher);
        }

        return CourseOutput.fromModel(courseRepository.save(course));
    }

    @Override
    @Transactional
    public void deleteCourse(Long id) {
        Course course = courseRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + id));
        course.setDeleted(true);
        courseRepository.save(course);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CourseOutput> getAllCourses() {
        return courseRepository.findAllActiveWithTeacher().stream()
                .map(CourseOutput::fromModel)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public CourseOutput getCourseById(Long id) {
        Course course = courseRepository.findActiveByIdWithTeacher(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + id));
        return CourseOutput.fromModel(course);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CourseOutput> getCoursesByTeacher(Long teacherId) {
        if (teacherRepository.findByIdAndDeletedFalse(teacherId).isEmpty()) {
            throw new ResourceNotFoundException("Teacher not found with id: " + teacherId);
        }
        return courseRepository.findByTeacherIdAndDeletedFalse(teacherId).stream()
                .map(CourseOutput::fromModel)
                .toList();
    }
}

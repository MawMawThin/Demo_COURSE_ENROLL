package com.example.demo_course_enroll.service;

import com.example.demo_course_enroll.exception.ConflictException;
import com.example.demo_course_enroll.exception.ResourceNotFoundException;
import com.example.demo_course_enroll.model.Course;
import com.example.demo_course_enroll.model.Student;
import com.example.demo_course_enroll.payload.input.StudentInput;
import com.example.demo_course_enroll.payload.output.CourseOutput;
import com.example.demo_course_enroll.payload.output.StudentOutput;
import com.example.demo_course_enroll.repository.CourseRepository;
import com.example.demo_course_enroll.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements IStudentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    @Override
    @Transactional
    public StudentOutput registerStudent(StudentInput input) {
        Student student = StudentInput.toModel(input);
        student.setDeleted(false);
        return StudentOutput.fromModel(studentRepository.save(student));
    }

    @Override
    @Transactional
    public StudentOutput updateStudent(Long id, StudentInput input) {
        Student student = studentRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
        StudentInput.updateModel(input, student);
        return StudentOutput.fromModel(studentRepository.save(student));
    }

    @Override
    @Transactional
    public void deleteStudent(Long id) {
        Student student = studentRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
        student.setDeleted(true);
        studentRepository.save(student);
    }

    @Override
    @Transactional(readOnly = true)
    public List<StudentOutput> getAllStudents() {
        return studentRepository.findByDeletedFalse().stream()
                .map(StudentOutput::fromModel)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public StudentOutput getStudentById(Long id) {
        Student student = studentRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
        return StudentOutput.fromModel(student);
    }

    @Override
    @Transactional
    public CourseOutput enrollCourse(Long studentId, Long courseId) {
        Student student = studentRepository.findByIdAndDeletedFalse(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + studentId));
        Course course = courseRepository.findByIdAndDeletedFalse(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + courseId));

        boolean alreadyEnrolled = course.getStudents().stream()
                .anyMatch(s -> s.getId().equals(studentId));
        if (alreadyEnrolled) {
            throw new ConflictException("Student is already enrolled in this course");
        }

        course.getStudents().add(student);
        Course saved = courseRepository.save(course);
        return CourseOutput.fromModel(saved);
    }

    @Override
    @Transactional
    public void unenrollCourse(Long studentId, Long courseId) {
        Student student = studentRepository.findByIdAndDeletedFalse(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + studentId));
        Course course = courseRepository.findByIdAndDeletedFalse(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + courseId));

        boolean removed = course.getStudents().removeIf(s -> s.getId().equals(student.getId()));
        if (!removed) {
            throw new ResourceNotFoundException("Student is not enrolled in this course");
        }

        courseRepository.save(course);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CourseOutput> getCoursesByStudent(Long studentId) {
        if (studentRepository.findByIdAndDeletedFalse(studentId).isEmpty()) {
            throw new ResourceNotFoundException("Student not found with id: " + studentId);
        }
        return courseRepository.findByStudentIdAndDeletedFalse(studentId).stream()
                .map(CourseOutput::fromModel)
                .toList();
    }
}

package com.example.demo_course_enroll.controller;

import com.example.demo_course_enroll.payload.input.StudentInput;
import com.example.demo_course_enroll.payload.output.CourseOutput;
import com.example.demo_course_enroll.payload.output.StudentOutput;
import com.example.demo_course_enroll.service.IStudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {

    private final IStudentService studentService;

    @GetMapping
    public ResponseEntity<List<StudentOutput>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentOutput> getStudentById(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @GetMapping("/{id}/courses")
    public ResponseEntity<List<CourseOutput>> getCoursesByStudent(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.getCoursesByStudent(id));
    }

    @PostMapping
    public ResponseEntity<StudentOutput> registerStudent(@Valid @RequestBody StudentInput input) {
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.registerStudent(input));
    }

    @PostMapping("/{id}/courses/{courseId}")
    public ResponseEntity<CourseOutput> enrollCourse(@PathVariable Long id,
                                                     @PathVariable Long courseId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.enrollCourse(id, courseId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentOutput> updateStudent(@PathVariable Long id,
                                                       @Valid @RequestBody StudentInput input) {
        return ResponseEntity.ok(studentService.updateStudent(id, input));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}/courses/{courseId}")
    public ResponseEntity<Void> unenrollCourse(@PathVariable Long id,
                                               @PathVariable Long courseId) {
        studentService.unenrollCourse(id, courseId);
        return ResponseEntity.noContent().build();
    }
}

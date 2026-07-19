package com.example.demo_course_enroll.controller;

import com.example.demo_course_enroll.payload.input.CourseInput;
import com.example.demo_course_enroll.payload.output.CourseOutput;
import com.example.demo_course_enroll.service.ICourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {

    private final ICourseService courseService;

    @GetMapping
    public ResponseEntity<List<CourseOutput>> getAllCourses() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseOutput> getCourseById(@PathVariable Long id) {
        return ResponseEntity.ok(courseService.getCourseById(id));
    }

    @GetMapping("/by-teacher/{teacherId}")
    public ResponseEntity<List<CourseOutput>> getCoursesByTeacher(@PathVariable Long teacherId) {
        return ResponseEntity.ok(courseService.getCoursesByTeacher(teacherId));
    }

    @PostMapping
    public ResponseEntity<CourseOutput> registerCourse(@Valid @RequestBody CourseInput input) {
        return ResponseEntity.status(HttpStatus.CREATED).body(courseService.registerCourse(input));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseOutput> updateCourse(@PathVariable Long id,
                                                     @Valid @RequestBody CourseInput input) {
        return ResponseEntity.ok(courseService.updateCourse(id, input));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }
}

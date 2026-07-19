package com.example.demo_course_enroll.controller;

import com.example.demo_course_enroll.payload.input.TeacherInput;
import com.example.demo_course_enroll.payload.output.TeacherOutput;
import com.example.demo_course_enroll.service.ITeacherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teachers")
@RequiredArgsConstructor
public class TeacherController {

    private final ITeacherService teacherService;

    @GetMapping
    public ResponseEntity<List<TeacherOutput>> getAllTeachers() {
        return ResponseEntity.ok(teacherService.getAllTeachers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherOutput> getTeacherById(@PathVariable Long id) {
        return ResponseEntity.ok(teacherService.getTeacherById(id));
    }

    @PostMapping
    public ResponseEntity<TeacherOutput> registerTeacher(@Valid @RequestBody TeacherInput input) {
        return ResponseEntity.status(HttpStatus.CREATED).body(teacherService.registerTeacher(input));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeacherOutput> updateTeacher(@PathVariable Long id,
                                                       @Valid @RequestBody TeacherInput input) {
        return ResponseEntity.ok(teacherService.updateTeacher(id, input));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable Long id) {
        teacherService.deleteTeacher(id);
        return ResponseEntity.noContent().build();
    }
}

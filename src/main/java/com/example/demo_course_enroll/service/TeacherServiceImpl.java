package com.example.demo_course_enroll.service;

import com.example.demo_course_enroll.model.Teacher;
import com.example.demo_course_enroll.payload.input.TeacherInput;
import com.example.demo_course_enroll.payload.output.TeacherOutput;
import com.example.demo_course_enroll.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements ITeacherService {

    private final TeacherRepository teacherRepository;

    @Override
    public String registerTeacher(TeacherInput input) {
        if (input.getName() == null || input.getName().isBlank()) {
            throw new RuntimeException("Teacher name is required");
        }
        Teacher teacher = TeacherInput.toModel(input);
        teacher.setDeleted(false);
        teacherRepository.save(teacher);
        return "Teacher registered successfully";
    }

    @Override
    public String updateTeacher(Long id, TeacherInput input) {
        Teacher teacher = teacherRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new RuntimeException("Teacher not found with id: " + id));
        TeacherInput.updateModel(input, teacher);
        teacherRepository.save(teacher);
        return "Teacher updated successfully";
    }

    @Override
    public String deleteTeacher(Long id) {
        Teacher teacher = teacherRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new RuntimeException("Teacher not found with id: " + id));
        teacher.setDeleted(true);
        teacherRepository.save(teacher);
        return "Teacher deleted successfully";
    }

    @Override
    public List<TeacherOutput> getAllTeachers() {
        return teacherRepository.findByDeletedFalse().stream()
                .map(TeacherOutput::fromModel).toList();
    }

    @Override
    public TeacherOutput getTeacherById(Long id) {
        Teacher teacher = teacherRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new RuntimeException("Teacher not found with id: " + id));
        return TeacherOutput.fromModel(teacher);
    }
}

package com.example.demo_course_enroll.service;

import com.example.demo_course_enroll.exception.ResourceNotFoundException;
import com.example.demo_course_enroll.model.Teacher;
import com.example.demo_course_enroll.payload.input.TeacherInput;
import com.example.demo_course_enroll.payload.output.TeacherOutput;
import com.example.demo_course_enroll.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements ITeacherService {

    private final TeacherRepository teacherRepository;

    @Override
    @Transactional
    public TeacherOutput registerTeacher(TeacherInput input) {
        Teacher teacher = TeacherInput.toModel(input);
        teacher.setDeleted(false);
        return TeacherOutput.fromModel(teacherRepository.save(teacher));
    }

    @Override
    @Transactional
    public TeacherOutput updateTeacher(Long id, TeacherInput input) {
        Teacher teacher = teacherRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found with id: " + id));
        TeacherInput.updateModel(input, teacher);
        return TeacherOutput.fromModel(teacherRepository.save(teacher));
    }

    @Override
    @Transactional
    public void deleteTeacher(Long id) {
        Teacher teacher = teacherRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found with id: " + id));
        teacher.setDeleted(true);
        teacherRepository.save(teacher);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TeacherOutput> getAllTeachers() {
        return teacherRepository.findByDeletedFalse().stream()
                .map(TeacherOutput::fromModel)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public TeacherOutput getTeacherById(Long id) {
        Teacher teacher = teacherRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found with id: " + id));
        return TeacherOutput.fromModel(teacher);
    }
}

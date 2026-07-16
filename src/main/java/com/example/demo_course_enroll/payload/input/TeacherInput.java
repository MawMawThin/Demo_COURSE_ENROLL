package com.example.demo_course_enroll.payload.input;

import com.example.demo_course_enroll.model.Teacher;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeacherInput {

    private String name;

    @Email(message = "Invalid email")
    private String email;

    private String phno;

    public static Teacher toModel(TeacherInput input) {
        return Teacher.builder()
                .name(input.getName())
                .email(input.getEmail())
                .phno(input.getPhno())
                .build();
    }


    public static Teacher updateModel(TeacherInput input, Teacher teacher) {
        if (input.getName() != null) {
            teacher.setName(input.getName());
        }
        if (input.getEmail() != null) {
            teacher.setEmail(input.getEmail());
        }
        if (input.getPhno() != null) {
            teacher.setPhno(input.getPhno());
        }
        return teacher;
    }
}

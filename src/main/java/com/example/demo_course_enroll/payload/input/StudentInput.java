package com.example.demo_course_enroll.payload.input;

import com.example.demo_course_enroll.model.Student;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentInput {

    @NotBlank(message = "Student name is required")
    private String name;

    @Email(message = "Invalid email")
    private String email;

    @NotBlank(message = "Roll number is required")
    private String rollNo;

    public static Student toModel(StudentInput input) {
        return Student.builder()
                .name(input.getName())
                .email(input.getEmail())
                .rollNo(input.getRollNo())
                .deleted(false)
                .build();
    }

    public static Student updateModel(StudentInput input, Student student) {
        if (input.getName() != null) {
            student.setName(input.getName());
        }
        if (input.getEmail() != null) {
            student.setEmail(input.getEmail());
        }
        if (input.getRollNo() != null) {
            student.setRollNo(input.getRollNo());
        }
        return student;
    }
}

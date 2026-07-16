package com.example.demo_course_enroll.payload.output;

import com.example.demo_course_enroll.model.Teacher;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonPropertyOrder({"id", "name", "email", "phno"})
public class TeacherOutput {

    private Long id;
    private String name;
    private String email;
    private String phno;

    public static TeacherOutput fromModel(Teacher teacher) {
        TeacherOutput output = new TeacherOutput();
        output.setId(teacher.getId());
        output.setName(teacher.getName());
        output.setEmail(teacher.getEmail());
        output.setPhno(teacher.getPhno());
        return output;
    }
}

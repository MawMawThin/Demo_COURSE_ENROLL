package com.example.demo_course_enroll.payload.output;

import com.example.demo_course_enroll.model.Student;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonPropertyOrder({"id", "name", "email", "rollNo"})
public class StudentOutput {

    private Long id;
    private String name;
    private String email;
    private String rollNo;

    public static StudentOutput fromModel(Student student) {
        StudentOutput output = new StudentOutput();
        output.setId(student.getId());
        output.setName(student.getName());
        output.setEmail(student.getEmail());
        output.setRollNo(student.getRollNo());
        return output;
    }
}

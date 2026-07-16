package com.example.demo_course_enroll.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Entity
@Getter
@Setter
@Table(name = "student")
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Student name is required")
    @Column(nullable = false)
    private String name;

    @Email(message="Invalid Email")
    @Column(unique=true)
    private String email;

    @Column(unique = true,nullable = false)
    private String rollNo;


    @ManyToMany(mappedBy = "students")
    private List<Course> courses = new ArrayList<>();


}

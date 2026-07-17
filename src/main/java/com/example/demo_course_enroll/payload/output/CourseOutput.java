package com.example.demo_course_enroll.payload.output;

import com.example.demo_course_enroll.model.Course;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonPropertyOrder({"id", "courseName", "durationHours", "notes", "teacherId", "teacherName"})
public class CourseOutput {

    private Long id;
    private String courseName;
    private Integer durationHours;
    private String notes;
    private Long teacherId;
    private String teacherName;

    public static CourseOutput fromModel(Course course) {
        CourseOutput output = new CourseOutput();
        output.setId(course.getId());
        output.setCourseName(course.getCourseName());
        output.setDurationHours(course.getDurationHours());
        output.setNotes(course.getNotes());

        if (course.getTeacher() != null) {
            output.setTeacherId(course.getTeacher().getId());
            output.setTeacherName(course.getTeacher().getName());
        }
        return output;
    }
}

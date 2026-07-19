package com.example.demo_course_enroll.payload.input;

import com.example.demo_course_enroll.model.Course;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseInput {

    @NotBlank(message = "Course name is required")
    private String courseName;
    private Integer durationHours;
    private String notes;
    private Long teacherId;

    public static Course toModel(CourseInput input) {
        return Course.builder()
                .courseName(input.getCourseName())
                .durationHours(input.getDurationHours())
                .notes(input.getNotes())
                .deleted(false)
                .build();
    }

    public static Course updateModel(CourseInput input, Course course) {
        if (input.getCourseName() != null) {
            course.setCourseName(input.getCourseName());
        }
        if (input.getDurationHours() != null) {
            course.setDurationHours(input.getDurationHours());
        }
        if (input.getNotes() != null) {
            course.setNotes(input.getNotes());
        }
        return course;
    }
}

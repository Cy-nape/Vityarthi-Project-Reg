package service;

import domain.Course;

import java.util.List;

public interface CourseService {
    void addCourse(Course course);
    void updateCourse(Course course);
    Course findCourseByCode(String code);
    List<Course> listAllCourses();
    void removeCourse(String code);
}
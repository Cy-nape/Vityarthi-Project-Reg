package service;

import domain.Course;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CourseServiceImpl implements CourseService {
    private final List<Course> courses;

    public CourseServiceImpl() {
        this.courses = new ArrayList<>();
    }

    @Override
    public void addCourse(Course course) {
        if (findCourseByCode(course.getCode().getCode()) != null) {
            throw new IllegalArgumentException("Course code already exists: " + course.getCode());
        }
        courses.add(course);
    }

    @Override
    public void updateCourse(Course course) {
        Course existing = findCourseByCode(course.getCode().getCode());
        if (existing == null) {
            throw new IllegalArgumentException("Course not found: " + course.getCode());
        }
        existing.setTitle(course.getTitle());
        existing.setCredits(course.getCredits());
        existing.setInstructor(course.getInstructor());
        existing.setSemester(course.getSemester());
        existing.setDepartment(course.getDepartment());
    }

    @Override
    public Course findCourseByCode(String code) {
        Optional<Course> course = courses.stream()
                .filter(c -> c.getCode().getCode().equals(code))
                .findFirst();
        return course.orElse(null);
    }

    @Override
    public List<Course> listAllCourses() {
        return new ArrayList<>(courses);
    }

    @Override
    public void removeCourse(String code) {
        Course course = findCourseByCode(code);
        if (course != null) {
            courses.remove(course);
        }
    }
}
package service;

import domain.Course;
import domain.Enrollment;
import domain.Grade;
import domain.Student;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EnrollmentService {

    private final List<Enrollment> enrollments;
    private final StudentService studentService;
    private final CourseService courseService;

    public EnrollmentService(StudentService studentService, CourseService courseService) {
        this.enrollments = new ArrayList<>();
        this.studentService = studentService;
        this.courseService = courseService;
    }

    public void enrollStudentInCourse(String studentId, String courseCode, LocalDate enrollmentDate) {
        Student student = studentService.findStudentById(studentId);
        Course course = courseService.findCourseByCode(courseCode);
        if (student == null) {
            throw new IllegalArgumentException("Student not found: " + studentId);
        }
        if (course == null) {
            throw new IllegalArgumentException("Course not found: " + courseCode);
        }
        Enrollment enrollment = new Enrollment(student, course, enrollmentDate);
        enrollments.add(enrollment);
        student.addEnrollment(enrollment);
    }

    public void assignGrade(String studentId, String courseCode, Grade grade) {
        Enrollment enrollment = findEnrollment(studentId, courseCode);
        if (enrollment == null) {
            throw new IllegalArgumentException("Enrollment not found for student " + studentId + " in course " + courseCode);
        }
        enrollment.setGrade(grade);
    }

    public Enrollment findEnrollment(String studentId, String courseCode) {
        return enrollments.stream()
                .filter(e -> e.getStudent().getId().equals(studentId)
                        && e.getCourse().getCode().getCode().equals(courseCode))
                .findFirst()
                .orElse(null);
    }

    public List<Enrollment> listEnrollmentsByStudent(String studentId) {
        return enrollments.stream()
                .filter(e -> e.getStudent().getId().equals(studentId))
                .collect(Collectors.toList());
    }

    public List<Enrollment> listEnrollmentsByCourse(String courseCode) {
        return enrollments.stream()
                .filter(e -> e.getCourse().getCode().getCode().equals(courseCode))
                .collect(Collectors.toList());
    }

    public List<Enrollment> listAllEnrollments() {
        return new ArrayList<>(enrollments);
    }
}
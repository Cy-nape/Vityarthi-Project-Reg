package domain;

import java.time.LocalDate;

public class Enrollment {
    private Student student;
    private Course course;
    private Grade grade; 
    private LocalDate enrollmentDate;

    public Enrollment(Student student, Course course, LocalDate enrollmentDate) {
        this.student = student;
        this.course = course;
        this.enrollmentDate = enrollmentDate;
        this.grade = null;
    }

    public Student getStudent() {
        return student;
    }

    public Course getCourse() {
        return course;
    }

    public Grade getGrade() {
        return grade;
    }

    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Enrollment{" +
                "student=" + student.getFullName() +
                ", course=" + course.getTitle() +
                ", grade=" + (grade != null ? grade : "N/A") +
                ", enrolled on=" + enrollmentDate +
                '}';
    }
}
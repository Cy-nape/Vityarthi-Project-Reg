package domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Student extends Person {
    private String email;
    private Status status;
    private LocalDate enrollmentDate;
    private List<Enrollment> enrollments;

    public Student(String id, String regNo, String fullName, String email, Status status, LocalDate enrollmentDate) {
        super(id, regNo, fullName);
        this.email = email;
        this.status = status;
        this.enrollmentDate = enrollmentDate;
        this.enrollments = new ArrayList<>();
    }

    public String getEmail() {
        return email;
    }

    public Status getStatus() {
        return status;
    }

    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }

    public List<Enrollment> getEnrollments() {
        return enrollments;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setEnrollmentDate(LocalDate enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public void addEnrollment(Enrollment enrollment) {
        this.enrollments.add(enrollment);
    }

    public void removeEnrollment(Enrollment enrollment) {
        this.enrollments.remove(enrollment);
    }

    public void printProfile() {
        System.out.println("Student Profile");
        System.out.println("----------------");
        System.out.println(this);
        System.out.println("Email: " + email);
        System.out.println("Status: " + status);
        System.out.println("Enrollment Date: " + enrollmentDate);
        System.out.println("Enrolled Courses:");
        if (enrollments.isEmpty()) {
            System.out.println("  None");
        } else {
            for (Enrollment e : enrollments) {
                System.out.println("  - " + e.getCourse().getTitle() + " (" + e.getCourse().getCode() + ")");
            }
        }
        System.out.println();
    }

    public void printTranscript() {
        System.out.println("Transcript for " + getFullName());
        System.out.println("--------------------------------");
        double totalPoints = 0;
        int totalCredits = 0;
        for (Enrollment e : enrollments) {
            Course c = e.getCourse();
            Grade g = e.getGrade();
            System.out.printf("%s (%s) - Credits: %d, Grade: %s\n", c.getTitle(), c.getCode(), c.getCredits(), g);
            if (g != null) {
                totalPoints += g.getGradePoint() * c.getCredits();
                totalCredits += c.getCredits();
            }
        }
        double gpa = totalCredits > 0 ? totalPoints / totalCredits : 0.0;
        System.out.printf("GPA: %.2f\n", gpa);
        System.out.println();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
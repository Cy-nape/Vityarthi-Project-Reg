package domain;

import java.util.ArrayList;
import java.util.List;

public class Instructor extends Person {
    private String email;
    private String department;
    private List<Course> coursesTaught;

    public Instructor(String id, String regNo, String fullName, String email, String department) {
        super(id, regNo, fullName);
        this.email = email;
        this.department = department;
        this.coursesTaught = new ArrayList<>();
    }

    public String getEmail() {
        return email;
    }

    public String getDepartment() {
        return department;
    }

    public List<Course> getCoursesTaught() {
        return coursesTaught;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void addCourse(Course course) {
        this.coursesTaught.add(course);
    }

    public void removeCourse(Course course) {
        this.coursesTaught.remove(course);
    }

    public void printProfile() {
        System.out.println("Instructor Profile");
        System.out.println("------------------");
        System.out.println(this);
        System.out.println("Email: " + email);
        System.out.println("Department: " + department);
        System.out.println("Courses Taught:");
        if (coursesTaught.isEmpty()) {
            System.out.println("  None");
        } else {
            for (Course c : coursesTaught) {
                System.out.println("  - " + c.getTitle() + " (" + c.getCode() + ")");
            }
        }
        System.out.println();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
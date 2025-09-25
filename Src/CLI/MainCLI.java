package cli;

import config.AppConfig;
import domain.*;
import io.CsvReader;
import service.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class MainCLI {

    private final StudentService studentService;
    private final CourseService courseService;
    private final EnrollmentService enrollmentService;
    private final Scanner scanner = new Scanner(System.in);
    private final CsvReader csvReader;

    public MainCLI() {
        AppConfig config = AppConfig.getInstance();
        this.studentService = config.getStudentService();
        this.courseService = config.getCourseService();
        this.enrollmentService = config.getEnrollmentService();
        this.csvReader = new CsvReader(studentService, courseService);
    }

    public void start() {
        System.out.println("Welcome to Course & Student Management System!");
        boolean exit = false;
        while (!exit) {
            printMenu();
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1" -> addStudent();
                case "2" -> addCourse();
                case "3" -> enrollStudentInCourse();
                case "4" -> assignGrade();
                case "5" -> listStudents();
                case "6" -> listCourses();
                case "7" -> printStudentProfile();
                case "8" -> printStudentTranscript();
                case "9" -> importFromCSV();
                case "10" -> exit = true;
                default -> System.out.println("Invalid option. Try again.");
            }
        }
        System.out.println("Exiting. Goodbye!");
    }

    private void printMenu() {
        System.out.println("\n========== CCRM Main Menu ==========");
        System.out.println("1. Add Student");
        System.out.println("2. Add Course");
        System.out.println("3. Enroll Student in Course");
        System.out.println("4. Assign Grade");
        System.out.println("5. List All Students");
        System.out.println("6. List All Courses");
        System.out.println("7. Print Student Profile");
        System.out.println("8. Print Student Transcript");
        System.out.println("9. Import Students & Courses from CSV");
        System.out.println("10. Exit");
        System.out.println("====================================");
        System.out.print("Enter choice: ");
    }

    private void addStudent() {
        try {
            System.out.print("Enter student ID: ");
            String id = scanner.nextLine().trim();
            System.out.print("Enter registration number: ");
            String regNo = scanner.nextLine().trim();
            System.out.print("Enter full name: ");
            String fullName = scanner.nextLine().trim();
            System.out.print("Enter email: ");
            String email = scanner.nextLine().trim();
            System.out.print("Enter status (ACTIVE, INACTIVE, SUSPENDED): ");
            Status status = Status.valueOf(scanner.nextLine().trim().toUpperCase());
            System.out.print("Enter enrollment date (YYYY-MM-DD): ");
            LocalDate date = LocalDate.parse(scanner.nextLine().trim());

            Student student = new Student(id, regNo, fullName, email, status, date);
            studentService.addStudent(student);
            System.out.println("Student added successfully.");
        } catch (Exception e) {
            System.out.println("Error adding student: " + e.getMessage());
        }
    }

    private void addCourse() {
        try {
            System.out.print("Enter course code: ");
            String code = scanner.nextLine().trim();
            System.out.print("Enter title: ");
            String title = scanner.nextLine().trim();
            System.out.print("Enter credits: ");
            int credits = Integer.parseInt(scanner.nextLine().trim());
            System.out.print("Enter department: ");
            String dept = scanner.nextLine().trim();
            System.out.print("Enter semester (SPRING, SUMMER, FALL): ");
            Semester semester = Semester.valueOf(scanner.nextLine().trim().toUpperCase());

            Course course = new Course.Builder(code)
                    .title(title)
                    .credits(credits)
                    .department(dept)
                    .semester(semester)
                    .build();

            courseService.addCourse(course);
            System.out.println("Course added successfully.");
        } catch (Exception e) {
            System.out.println("Error adding course: " + e.getMessage());
        }
    }

    private void enrollStudentInCourse() {
        try {
            System.out.print("Enter student ID: ");
            String studentId = scanner.nextLine().trim();
            System.out.print("Enter course code: ");
            String courseCode = scanner.nextLine().trim();
            System.out.print("Enter enrollment date (YYYY-MM-DD): ");
            LocalDate date = LocalDate.parse(scanner.nextLine().trim());

            enrollmentService.enrollStudentInCourse(studentId, courseCode, date);
            System.out.println("Enrollment successful.");
        } catch (Exception e) {
            System.out.println("Error enrolling student: " + e.getMessage());
        }
    }

    private void assignGrade() {
        try {
            System.out.print("Enter student ID: ");
            String studentId = scanner.nextLine().trim();
            System.out.print("Enter course code: ");
            String courseCode = scanner.nextLine().trim();
            System.out.print("Enter grade (S, A, B, C, D, E, F): ");
            Grade grade = Grade.valueOf(scanner.nextLine().trim().toUpperCase());

            enrollmentService.assignGrade(studentId, courseCode, grade);
            System.out.println("Grade assigned successfully.");
        } catch (Exception e) {
            System.out.println("Error assigning grade: " + e.getMessage());
        }
    }

    private void listStudents() {
        List<Student> students = studentService.listAllStudents();
        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }
        System.out.println("All Students:");
        for (Student s : students) {
            System.out.println(s);
        }
    }

    private void listCourses() {
        List<Course> courses = courseService.listAllCourses();
        if (courses.isEmpty()) {
            System.out.println("No courses found.");
            return;
        }
        System.out.println("All Courses:");
        for (Course c : courses) {
            System.out.println(c);
        }
    }

    private void printStudentProfile() {
        System.out.print("Enter student ID: ");
        String id = scanner.nextLine().trim();
        Student student = studentService.findStudentById(id);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }
        student.printProfile();
    }

    private void printStudentTranscript() {
        System.out.print("Enter student ID: ");
        String id = scanner.nextLine().trim();
        Student student = studentService.findStudentById(id);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }
        student.printTranscript();
    }

    private void importFromCSV() {
        System.out.print("Path to students.csv: ");
        String studentPath = scanner.nextLine();
        System.out.print("Path to courses.csv: ");
        String coursePath = scanner.nextLine();
        csvReader.importStudents(studentPath);
        csvReader.importCourses(coursePath);
    }

    public static void main(String[] args) {
        new MainCLI().start();
    }
}
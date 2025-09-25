package io;

import domain.*;
import service.*;

import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.List;

public class CsvReader {

    private StudentService studentService;
    private CourseService courseService;

    public CsvReader(StudentService studentService, CourseService courseService) {
        this.studentService = studentService;
        this.courseService = courseService;
    }

    public void importStudents(String path) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(path));
            lines.remove(0); // skip header

            for (String line : lines) {
                String[] fields = line.split(",");
                Student student = new Student(
                        fields[0], 
                        fields[1], 
                        fields[2], 
                        fields[3], 
                        Status.valueOf(fields[4].toUpperCase()),
                        LocalDate.parse(fields[5]) 
                );
                studentService.addStudent(student);
            }

            System.out.println("Students imported successfully from " + path);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    public void importCourses(String path) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(path));
            lines.remove(0); 

            for (String line : lines) {
                String[] fields = line.split(",");
                Course course = new Course.Builder(fields[0])
                        .title(fields[1])
                        .credits(Integer.parseInt(fields[2]))
                        .department(fields[3])
                        .semester(Semester.valueOf(fields[4].toUpperCase()))
                        .build();
                courseService.addCourse(course);
            }

            System.out.println("Courses imported successfully from " + path);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
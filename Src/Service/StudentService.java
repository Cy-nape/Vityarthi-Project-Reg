package service;

import domain.Student;

import java.util.List;

public interface StudentService {
    void addStudent(Student student);
    void updateStudent(Student student);
    Student findStudentById(String id);
    List<Student> listAllStudents();
    void deactivateStudent(String id);
}
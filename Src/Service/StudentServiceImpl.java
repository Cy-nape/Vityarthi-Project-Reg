package service;

import domain.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentServiceImpl implements StudentService {

    private final List<Student> students;

    public StudentServiceImpl() {
        this.students = new ArrayList<>();
    }

    @Override
    public void addStudent(Student student) {
        if (findStudentById(student.getId()) != null) {
            throw new IllegalArgumentException("Student with ID already exists: " + student.getId());
        }
        students.add(student);
    }

    @Override
    public void updateStudent(Student student) {
        Student existing = findStudentById(student.getId());
        if (existing == null) {
            throw new IllegalArgumentException("Student not found: " + student.getId());
        }
        existing.setFullName(student.getFullName());
        existing.setEmail(student.getEmail());
        existing.setStatus(student.getStatus());
        existing.setEnrollmentDate(student.getEnrollmentDate());
    }

    @Override
    public Student findStudentById(String id) {
        Optional<Student> student = students.stream()
                .filter(s -> s.getId().equals(id))
                .findFirst();
        return student.orElse(null);
    }

    @Override
    public List<Student> listAllStudents() {
        return new ArrayList<>(students);
    }

    @Override
    public void deactivateStudent(String id) {
        Student student = findStudentById(id);
        if (student != null) {
            student.setStatus(domain.Status.INACTIVE);
        }
    }
}
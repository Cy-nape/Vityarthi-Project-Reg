# Student Management System

A comprehensive Java-based student management system designed to handle course enrollment, grading, and academic records management for educational institutions.

## 🚀 Features

- **Student Management**: Create, update, and manage student profiles with enrollment tracking
- **Course Management**: Handle course creation with detailed information including credits, instructors, and departments
- **Enrollment System**: Manage student course enrollments with grade tracking
- **GPA Calculation**: Automatic GPA computation based on grades and credit hours
- **Transcript Generation**: Generate detailed academic transcripts for students
- **Instructor Profiles**: Manage instructor information and course assignments

## 📁 Project Structure

```
src/
├── domain/
│   ├── Course.java          # Course entity with builder pattern
│   ├── Student.java         # Student entity extending Person
│   ├── Instructor.java      # Instructor entity extending Person
│   ├── Person.java          # Abstract base class for persons
│   ├── Enrollment.java      # Enrollment relationship entity
│   ├── Grade.java           # Grade enumeration (S, A, B, C, D, E, F)
│   ├── Semester.java        # Semester enumeration (FALL, INTERIM, WINTER)
│   └── Status.java          # Student status enumeration (ACTIVE, INACTIVE, SUSPENDED)
├── config/
│   └── AppConfig.java       # Application configuration with singleton pattern
└── service/
    ├── StudentService.java
    ├── CourseService.java
    └── EnrollmentService.java
```

## 🏗️ Architecture

### Domain Model

The system follows a domain-driven design approach with the following core entities:

- **Person**: Abstract base class for students and instructors
- **Student**: Manages student information, enrollments, and academic records
- **Instructor**: Handles instructor profiles and course assignments
- **Course**: Represents academic courses with immutable course codes
- **Enrollment**: Links students to courses with grades and enrollment dates

### Design Patterns

- **Builder Pattern**: Used in Course class for flexible object creation
- **Singleton Pattern**: Implemented in AppConfig for centralized configuration
- **Value Object**: CourseCode as an immutable value class
- **Enumeration**: Grade, Semester, and Status for type safety

## 🎯 Key Components

### Grade System
```java
Grade.S (10 points) - Excellent
Grade.A (9 points)  - Very Good
Grade.B (8 points)  - Good
Grade.C (7 points)  - Average
Grade.D (6 points)  - Below Average
Grade.E (5 points)  - Poor
Grade.F (0 points)  - Fail
```

### Course Builder Example
```java
Course course = new Course.Builder("CS101")
    .title("Introduction to Computer Science")
    .credits(3)
    .department("Computer Science")
    .semester(Semester.FALL)
    .instructor(instructor)
    .build();
```

## 🛠️ Usage

### Creating a Student
```java
Student student = new Student(
    "STU001", 
    "2024001", 
    "John Doe", 
    "john.doe@university.edu", 
    Status.ACTIVE, 
    LocalDate.now()
);
```

### Enrolling a Student in a Course
```java
Enrollment enrollment = new Enrollment(student, course, LocalDate.now());
student.addEnrollment(enrollment);
```

### Assigning Grades
```java
enrollment.setGrade(Grade.A);
```

### Generating Transcript
```java
student.printTranscript(); // Prints detailed transcript with GPA
```

## 🔧 Service Layer

The application uses a service-oriented architecture:

- **StudentService**: Handles student-related operations
- **CourseService**: Manages course operations
- **EnrollmentService**: Handles enrollment logic and dependencies

Services are configured through the `AppConfig` singleton for dependency injection.

## 📋 Requirements

- Java 8 or higher (uses LocalDate from java.time package)
- No external dependencies required for core functionality

## 🚀 Getting Started

1. Clone the repository:
```bash
git clone <repository-url>
cd student-management-system
```

2. Compile the Java files:
```bash
javac -d bin src/**/*.java
```

3. Run the application:
```bash
java -cp bin Main
```

## 🧪 Testing

The system includes comprehensive domain models that can be tested with:
- Unit tests for individual entities
- Integration tests for service layer
- End-to-end tests for complete workflows

## 🤝 Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request



---

*Built with ❤️ using Java and clean architecture principles*

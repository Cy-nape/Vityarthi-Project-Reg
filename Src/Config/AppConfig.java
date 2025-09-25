package config;

import service.*;

public class AppConfig {

    private static AppConfig instance;

    private StudentService studentService;
    private CourseService courseService;
    private EnrollmentService enrollmentService;

    private AppConfig() {
        this.studentService = new StudentServiceImpl();
        this.courseService = new CourseServiceImpl();
        this.enrollmentService = new EnrollmentService(studentService, courseService);
    }

    public static AppConfig getInstance() {
        if (instance == null) {
            synchronized (AppConfig.class) {
                if (instance == null) {
                    instance = new AppConfig();
                }
            }
        }
        return instance;
    }

    public StudentService getStudentService() {
        return studentService;
    }

    public CourseService getCourseService() {
        return courseService;
    }

    public EnrollmentService getEnrollmentService() {
        return enrollmentService;
    }
}
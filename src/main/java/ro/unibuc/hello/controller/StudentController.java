package ro.unibuc.hello.controller;


import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.unibuc.hello.data.Student;
import ro.unibuc.hello.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("api/v1/students")

public class StudentController {
    private final StudentService studentService;
    @Autowired
    MeterRegistry metricsRegistry;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    @Timed(value = "student.fetchAllStudents.time", description = "Time taken to return students")
    @Counted(value = "student.fetchAllStudents.count", description = "Times students were returned")
    public List<Student> fetchAllStudents(){
        return studentService.getAllStudents();
    }
}

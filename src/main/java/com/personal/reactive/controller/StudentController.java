package com.personal.reactive.controller;

import com.personal.reactive.model.Student;
import com.personal.reactive.model.StudentStatus;
import com.personal.reactive.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping(value = "/students")
public class StudentController {
    @Autowired
    StudentService service;

    @PostMapping(value = "/save")
    public Mono<Student> storeStudent(Student student) {
        return service.saveStudent(student);
    }

    @PutMapping(value = "/update")
    public Mono<Student> updateStudentData(Student student) {
        return service.updateStudent(student);
    }

    @GetMapping(value = "/status/{status}")
    public Mono<List<Student>> fetchByStatus(@PathVariable StudentStatus status) {
        return service.findByStatus(status);
    }

    @GetMapping(value = "/status/enrolled")
    public Mono<List<Student>> fetchAllEnrolled() {
        return service.findAllEnrolled();
    }

    @PostMapping(value = "/saveAll")
    public Flux<Student> saveAllStudents(@RequestBody List<Student> students) {
        return service.saveAllStudents(students);
    }

    @PutMapping(value = "/enroll/{studentId}")
    public Mono<Student> enrollStudent(@PathVariable String studentId) {
        return service.enrollStudent(studentId);
    }

    @GetMapping(value = "/fetch/{status}")
    public Flux<Student> findByStatus (@PathVariable StudentStatus status) {
        return service.findAllByStatus(status);
    }
}

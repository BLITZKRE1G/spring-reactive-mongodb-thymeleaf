package com.personal.reactive.service;

import com.personal.reactive.repository.StudentRepository;
import com.personal.reactive.model.Student;
import com.personal.reactive.model.StudentStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class StudentService {
    @Autowired
    StudentRepository repo;

    public Mono<Student> saveStudent(Student student) {
        return repo.save(student);
    }

    public Flux<Student> saveAllStudents(List<Student> students) {
        return repo.saveAll(students);
    }

    public Mono<Student> updateStudent(Student student) {
        return repo.findById(student.get_id())
                .flatMap(oldData -> {
                    log.info("Updated {} to {}", oldData, student);
                    return repo.save(student);
                }).switchIfEmpty(Mono.defer(() -> {
                    throw new ResourceAccessException(
                            "Resource with ID: [" + student.get_id() + "] does not exist in the DB!");
                }));
    }

    public Mono<List<Student>> findAllEnrolled() {
        List<Student> studentList = new ArrayList<>();
        return repo.findAll()
                .collectList()
                .map(students -> {
                    for (Student student : students) {
                        if (student.getStatus().equals(StudentStatus.ENROLLED)) {
                            studentList.add(student);
                        }
                    }
                    return studentList;
                });
    }

    public Mono<List<Student>> findByStatus(StudentStatus studentStatus) {
        List<Student> studentList = new ArrayList<>();
        return repo.findAll()
                .collectList()
                .map(students -> {
                    for (Student student : students) {
                        if (student.getStatus().equals(studentStatus)) {
                            studentList.add(student);
                        }
                    }
                    return studentList;
                });
    }

    public Mono<Student> enrollStudent(String studentId) {
        return repo.findById(studentId)
                .flatMap(student -> {
                    log.info("Enrolled");
                    return repo.save(student);
                });
    }
}

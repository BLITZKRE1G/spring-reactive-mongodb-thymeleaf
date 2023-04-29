package com.personal.reactive.repository;

import com.personal.reactive.model.Student;
import com.personal.reactive.model.StudentStatus;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface StudentRepository extends ReactiveMongoRepository<Student, String> {
    Flux<Student> findByStatus(StudentStatus status);
}

package com.personal.reactive.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    private String _id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String department;
    private StudentStatus status;
    private String address;
}

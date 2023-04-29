package com.personal.reactive.model;

import lombok.Getter;

@Getter
public enum StudentStatus {
    ENROLLED("ENROLLED"),
    TERMINATED("TERMINATED"),
    SHORTLISTED("SHORTLISTED");
    private final String status;

    StudentStatus(String status) {
        this.status = status;
    }
}

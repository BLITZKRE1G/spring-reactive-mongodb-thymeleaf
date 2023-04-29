package com.personal.reactive.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StudentStatus {
    ENROLLED("ENROLLED"),
    TERMINATED("TERMINATED"),
    SHORTLISTED("SHORTLISTED");
    private final String status;
}

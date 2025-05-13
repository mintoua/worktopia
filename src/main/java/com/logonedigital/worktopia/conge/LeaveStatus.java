package com.logonedigital.worktopia.conge;

import lombok.Getter;

@Getter
public enum LeaveStatus {
    AWAITING("Awaiting approval"),
    APPROVED("Leave approved"),
    REJECTED("Leave rejected");

    private final String description;

    LeaveStatus(String description) {
        assert description != null  && !description.trim().isEmpty()  : "description cannot be null";
        this.description = description;
    }

}
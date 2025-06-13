package com.logonedigital.worktopia.user;

import lombok.Getter;

@Getter
public enum Role {
    EMPLOYE("ROLE_EMPLOYE"),
    RH("ROLE_RH"),
    ADMIN("ROLE_ADMIN");

    Role(String role) {
        assert role != null  && !role.trim().isEmpty()  : "Role cannot be null";
    }
}

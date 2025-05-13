package com.logonedigital.worktopia.user.password;

public record VerificationRequest(
        String email,
        String code
) {
}

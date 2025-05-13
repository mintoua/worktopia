package com.logonedigital.worktopia.user.password;

public record ResetPasswordRequest(
        String email,
        String newPassword
) {
}

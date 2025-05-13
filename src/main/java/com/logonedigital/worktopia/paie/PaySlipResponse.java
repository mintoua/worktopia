package com.logonedigital.worktopia.paie;

import java.util.UUID;

public record PaySlipResponse(
        String fileName,
        UUID publicId
) {
}

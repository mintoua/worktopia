package com.logonedigital.worktopia.auth;

import lombok.Builder;


@Builder
public record AuthenticationResponse(
        String token
) {

}



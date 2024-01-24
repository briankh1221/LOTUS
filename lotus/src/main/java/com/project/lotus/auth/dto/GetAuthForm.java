package com.project.lotus.auth.dto;

import com.project.lotus.common.config.security.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

public class GetAuthForm {

    @Getter
    @Builder
    @ToString
    public static class Response {

        private final Long idx;
        private final Role role;

    }
}

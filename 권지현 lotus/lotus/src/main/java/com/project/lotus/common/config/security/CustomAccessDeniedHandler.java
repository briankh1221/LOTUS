package com.project.lotus.common.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.lotus.common.config.security.dto.EntryPointErrorResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {

        ObjectMapper objectMapper = new ObjectMapper();

        EntryPointErrorResponse entryPointErrorResponse = new EntryPointErrorResponse();
        entryPointErrorResponse.setMsg("접근할 수 없습니다.");

        response.setStatus(403);
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(objectMapper.writeValueAsString(entryPointErrorResponse));

        // 아래 코드 잠시 Holding *24.01.23 jihyun
        // 경로 정해지면 수정 예정. 메인 페이지로 이동 예정 *24.01.19 jihyun
        // response.sendRedirect("/auth/signup");
    }
}

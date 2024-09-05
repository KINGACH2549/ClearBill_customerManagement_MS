package com.clearbill.customer.management.ms.security;


import com.clearbill.customer.management.ms.dto.error.ErrorDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;

@Component
public class AuthenticationService implements AuthenticationEntryPoint {

    @Autowired
    ErrorDTO errorDTO;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");

        PrintWriter writer = response.getWriter();
        errorDTO.setErrorType("Unauthorized Access");
        errorDTO.setPath(request.getRequestURI());
        errorDTO.setError(Collections.singletonList(authException.getMessage()));

        writer.println(errorDTO);

    }
}

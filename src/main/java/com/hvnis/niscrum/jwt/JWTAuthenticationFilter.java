package com.hvnis.niscrum.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import com.hvnis.niscrum.exception.TokenException;
import com.hvnis.niscrum.exception.handler.ExceptionHandler;

import lombok.AllArgsConstructor;

/**
 * @author hvnis
 */
@Component
@AllArgsConstructor
public class JWTAuthenticationFilter extends GenericFilterBean {

    private final TokenAuthenticationService tokenAuthenticationService;

    private final ExceptionHandler exceptionHandler;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        try {
            Authentication authentication = tokenAuthenticationService
                    .getAuthentication((HttpServletRequest) servletRequest);

            SecurityContextHolder.getContext().setAuthentication(authentication);

            filterChain.doFilter(servletRequest, servletResponse);

        } catch (TokenException ex) {
            exceptionHandler.handle((HttpServletRequest) servletRequest, (HttpServletResponse) servletResponse,
                    HttpStatus.UNAUTHORIZED, ex);
        }
    }

}

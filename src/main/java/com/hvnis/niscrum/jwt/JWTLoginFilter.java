package com.hvnis.niscrum.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import com.hvnis.niscrum.common.Constant;
import com.hvnis.niscrum.exception.handler.ExceptionHandler;
import com.hvnis.niscrum.security.CustomAuthentication;
import com.hvnis.niscrum.security.CustomAuthenticationManager;

/**
 * @author hvnis
 */
@Component
public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

    private final TokenAuthenticationService tokenAuthenticationService;
    
    private final ExceptionHandler exceptionResolver;

    public JWTLoginFilter(final CustomAuthenticationManager customAuthenticationManager,
            final TokenAuthenticationService tokenAuthenticationService, final ExceptionHandler exceptionResolver) {
        super(new AntPathRequestMatcher(Constant.LOGIN_PATH));
        setAuthenticationManager(customAuthenticationManager);
        this.tokenAuthenticationService = tokenAuthenticationService;
        this.exceptionResolver = exceptionResolver;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        return getAuthenticationManager().authenticate(new CustomAuthentication(username, password));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
            Authentication authResult) throws IOException, ServletException {
        tokenAuthenticationService.addAuthentication(response, (CustomAuthentication) authResult);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException failed) throws IOException, ServletException {
        exceptionResolver.handle(request, response, HttpStatus.UNAUTHORIZED, failed);
    }
}

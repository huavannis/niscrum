package com.hvnis.niscrum.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.hvnis.niscrum.common.Constant;
import com.hvnis.niscrum.jwt.JWTAuthenticationFilter;
import com.hvnis.niscrum.jwt.JWTLoginFilter;
import com.hvnis.niscrum.security.CustomAuthenticationEntryPoint;
import com.hvnis.niscrum.security.CustomAuthenticationManager;

import lombok.AllArgsConstructor;

/**
 * @author hvnis
 */
@AllArgsConstructor
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomAuthenticationManager customAuthenticationManager;

    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    private final JWTLoginFilter jwtLoginFilter;

    private final JWTAuthenticationFilter jwtAuthenticationFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests().antMatchers(HttpMethod.POST, Constant.LOGIN_PATH).permitAll()
                .anyRequest().authenticated().and().exceptionHandling()
                .authenticationEntryPoint(customAuthenticationEntryPoint).and()
                .addFilterBefore(jwtLoginFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return customAuthenticationManager;
    }

    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return customAuthenticationManager;
    }
}

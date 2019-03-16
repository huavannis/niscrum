package com.hvnis.niscrum.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.hvnis.niscrum.encoder.BCryptEncoder;
import com.hvnis.niscrum.jwt.JWTAuthenticationFilter;
import com.hvnis.niscrum.jwt.JWTLoginFilter;
import com.hvnis.niscrum.repository.PersistentLoginRepository;
import com.hvnis.niscrum.security.UserDetailsServiceImpl;

import lombok.AllArgsConstructor;

/**
 * @author hvnis
 */
@AllArgsConstructor
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsServiceImpl userDetailsService;

    private final PersistentLoginRepository persistentLoginRepository;

    private final BCryptEncoder bCryptEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests().antMatchers("/").permitAll() //
                .antMatchers(HttpMethod.POST, "/login").permitAll() //
                .antMatchers(HttpMethod.GET, "/login").permitAll() // For Test on Browser
                .anyRequest().authenticated().and().rememberMe().tokenRepository(persistentLoginRepository)
                .tokenValiditySeconds(1 * 24 * 60 * 60).and()
                .addFilterBefore(new JWTLoginFilter("/login", authenticationManager()),
                        UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptEncoder);
    }
}

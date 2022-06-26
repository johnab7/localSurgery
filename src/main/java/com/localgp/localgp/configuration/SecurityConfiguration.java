package com.localgp.localgp.configuration;


import com.localgp.localgp.repository.UserAuthRepository;
import com.localgp.localgp.security.JwtAuthenticationFilter;
import com.localgp.localgp.security.JwtAuthorizationFilter;
import com.localgp.localgp.service.serviceImplementation.UserPrincipalDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private UserPrincipalDetailsService userPrincipalDetailsService;
    private UserAuthRepository userAuthRepository;

    public SecurityConfiguration(UserPrincipalDetailsService userPrincipalDetailsService, UserAuthRepository userAuthRepository) {
        this.userPrincipalDetailsService = userPrincipalDetailsService;
        this.userAuthRepository = userAuthRepository;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http

                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()

                .addFilter(new JwtAuthenticationFilter(authenticationManager()))
                .addFilter(new JwtAuthorizationFilter(authenticationManager(), this.userAuthRepository))
                .authorizeRequests()

                .antMatchers(HttpMethod.POST, "/login").permitAll()
                .antMatchers(HttpMethod.POST, "/register").permitAll()
                //
                .antMatchers(HttpMethod.POST, "/doctor/availability").permitAll()
                .antMatchers(HttpMethod.GET, "/appointment/**").permitAll()
                .antMatchers(HttpMethod.GET, "/notifications").permitAll()
                .antMatchers("/admin/**").permitAll()
                .antMatchers("/doctor/**").permitAll()
                .antMatchers("/patient/**").permitAll()
                .antMatchers("/pharmacy/**").permitAll()
//                .antMatchers(HttpMethod.GET), "api/public/").permitAll()
                //PermitALL to Test built APIs

//                .antMatchers("/admin/**").hasRole("ADMIN")
//                .antMatchers("/doctor/**").hasRole("DOCTOR")
//                .antMatchers("/patient/**").hasRole("PATIENT")
//                .antMatchers("/pharmacy/**").hasRole("PHARMACY")
                .anyRequest().permitAll();
        http.cors();

    }

    @Bean
    DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(this.userPrincipalDetailsService);

        return daoAuthenticationProvider;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}



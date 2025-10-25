package com.digis.IHernandezProgramacionNCapas.Configuration;

import com.digis.IHernandezProgramacionNCapas.DAO.UserDetailsJPAService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    private final UserDetailsJPAService userDetailsJPAService;

    public SpringSecurityConfig(UserDetailsJPAService userDetailsJPAService) {

        this.userDetailsJPAService = userDetailsJPAService;

    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(configurer -> configurer
                .requestMatchers("/login").permitAll()
                .requestMatchers("/RecuperarContraseña").permitAll()
                .requestMatchers("/Usuario/cargamasiva").hasRole("ADMIN")
                .requestMatchers("/Usuario/action/0").hasRole("ADMIN")
                .requestMatchers("/Usuario/**").hasAnyRole("ADMIN", "USER")
                .anyRequest().authenticated())
                .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/Usuario", true))
                .userDetailsService(userDetailsJPAService);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}

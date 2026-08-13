package com.voluntrack.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                // Static Frontend Assets & HTML Pages
                .requestMatchers("/", "/index.html", "/login.html", "/register.html", "/events.html", "/event-detail.html",
                                 "/methodology.html", "/team.html",
                                 "/dashboard.html", "/admin-dashboard.html", "/charity-dashboard.html",
                                 "/volunteer-dashboard.html", "/donor-dashboard.html",
                                 "/css/**", "/js/**", "/images/**", "/h2-console/**", "/favicon.ico").permitAll()
                // Public Authentication Endpoints
                .requestMatchers("/api/auth/**").permitAll()

                // 1. Volunteer Specific Endpoints (Placed first to prevent any rule shadowing)
                .requestMatchers(HttpMethod.POST, "/api/events/*/apply").hasAuthority("ROLE_VOLUNTEER")
                .requestMatchers("/api/volunteers/me/**").hasAuthority("ROLE_VOLUNTEER")

                // 2. Donor Specific Endpoints
                .requestMatchers(HttpMethod.POST, "/api/events/*/donate").hasAuthority("ROLE_DONOR")
                .requestMatchers("/api/donations/me").hasAuthority("ROLE_DONOR")

                // 3. Charity & Admin Endpoints
                .requestMatchers(HttpMethod.POST, "/api/events/*/attendance").hasAnyAuthority("ROLE_CHARITY", "ROLE_ADMIN")
                .requestMatchers(HttpMethod.GET, "/api/events/*/applications").hasAnyAuthority("ROLE_CHARITY", "ROLE_ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/applications/*/decision").hasAnyAuthority("ROLE_CHARITY", "ROLE_ADMIN")
                .requestMatchers(HttpMethod.GET, "/api/reports/charity/*").hasAnyAuthority("ROLE_CHARITY", "ROLE_ADMIN")

                // 4. Admin Dedicated Endpoints
                .requestMatchers("/api/admin/**").hasAuthority("ROLE_ADMIN")

                // 5. General Event Creation & Public Event Retrieval (Placed after specific sub-resource rules)
                .requestMatchers(HttpMethod.POST, "/api/events").hasAnyAuthority("ROLE_CHARITY", "ROLE_ADMIN")
                .requestMatchers(HttpMethod.GET, "/api/events", "/api/events/**").permitAll()

                // All other endpoints require authentication
                .anyRequest().authenticated()
            );

        // Required for H2 Console frame display if enabled
        http.headers(headers -> headers.frameOptions(frame -> frame.disable()));

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}

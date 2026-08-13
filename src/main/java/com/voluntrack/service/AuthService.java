package com.voluntrack.service;

import com.voluntrack.dto.AuthResponse;
import com.voluntrack.dto.LoginRequest;
import com.voluntrack.dto.RegisterRequest;
import com.voluntrack.entity.CharityProfile;
import com.voluntrack.entity.User;
import com.voluntrack.entity.VolunteerProfile;
import com.voluntrack.repository.CharityProfileRepository;
import com.voluntrack.repository.UserRepository;
import com.voluntrack.repository.VolunteerProfileRepository;
import com.voluntrack.security.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final VolunteerProfileRepository volunteerProfileRepository;
    private final CharityProfileRepository charityProfileRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthService(UserRepository userRepository,
                       VolunteerProfileRepository volunteerProfileRepository,
                       CharityProfileRepository charityProfileRepository,
                       PasswordEncoder passwordEncoder,
                       AuthenticationManager authenticationManager,
                       JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.volunteerProfileRepository = volunteerProfileRepository;
        this.charityProfileRepository = charityProfileRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @Transactional
    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email is already registered: " + request.getEmail());
        }

        String role = request.getRole() != null ? request.getRole().toUpperCase() : "VOLUNTEER";

        if ("ADMIN".equals(role)) {
            throw new IllegalArgumentException("Public Admin registration is prohibited.");
        }

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(role)
                .build();

        user = userRepository.save(user);

        if ("VOLUNTEER".equals(role)) {
            VolunteerProfile vp = VolunteerProfile.builder()
                    .userId(user.getId())
                    .skills(request.getSkills())
                    .location(request.getLocation())
                    .availability(request.getAvailability())
                    .build();
            volunteerProfileRepository.save(vp);
        } else if ("CHARITY".equals(role)) {
            CharityProfile cp = CharityProfile.builder()
                    .userId(user.getId())
                    .orgName(request.getOrgName() != null ? request.getOrgName() : request.getName())
                    .regNumber(request.getRegNumber() != null ? request.getRegNumber() : "REG-" + System.currentTimeMillis())
                    .verifiedStatus(false)
                    .build();
            charityProfileRepository.save(cp);
        }

        String token = jwtUtil.generateToken(user.getEmail(), role, user.getId());
        return new AuthResponse(token, "Bearer", user.getId(), user.getName(), user.getEmail(), user.getRole());
    }

    public AuthResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid credentials"));

        String token = jwtUtil.generateToken(user.getEmail(), user.getRole(), user.getId());
        return new AuthResponse(token, "Bearer", user.getId(), user.getName(), user.getEmail(), user.getRole());
    }
}

package com.voluntrack.config;

import com.voluntrack.entity.*;
import com.voluntrack.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class AdminDataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final CharityProfileRepository charityProfileRepository;
    private final VolunteerProfileRepository volunteerProfileRepository;
    private final EventRepository eventRepository;
    private final AttendanceRepository attendanceRepository;
    private final EventDonationRepository eventDonationRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminDataInitializer(UserRepository userRepository,
                                CharityProfileRepository charityProfileRepository,
                                VolunteerProfileRepository volunteerProfileRepository,
                                EventRepository eventRepository,
                                AttendanceRepository attendanceRepository,
                                EventDonationRepository eventDonationRepository,
                                PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.charityProfileRepository = charityProfileRepository;
        this.volunteerProfileRepository = volunteerProfileRepository;
        this.eventRepository = eventRepository;
        this.attendanceRepository = attendanceRepository;
        this.eventDonationRepository = eventDonationRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        // 1. Seed Admin User
        if (!userRepository.existsByEmail("admin@voluntrack.com")) {
            User admin = User.builder()
                    .name("System Administrator")
                    .email("admin@voluntrack.com")
                    .password(passwordEncoder.encode("admin123"))
                    .role("ADMIN")
                    .build();
            userRepository.save(admin);
        }

        // 2. Seed Sample Charity User & Profile
        User charityUser = userRepository.findByEmail("charity@voluntrack.com").orElseGet(() -> {
            User u = User.builder()
                    .name("GreenEarth Foundation")
                    .email("charity@voluntrack.com")
                    .password(passwordEncoder.encode("charity123"))
                    .role("CHARITY")
                    .build();
            return userRepository.save(u);
        });

        if (!charityProfileRepository.findByUserId(charityUser.getId()).isPresent()) {
            CharityProfile cp = CharityProfile.builder()
                    .userId(charityUser.getId())
                    .orgName("GreenEarth Foundation")
                    .regNumber("NGO-501C3-9942")
                    .verifiedStatus(true)
                    .build();
            charityProfileRepository.save(cp);
        }

        // 3. Seed Sample Volunteer User & Profile
        User volUser = userRepository.findByEmail("volunteer@voluntrack.com").orElseGet(() -> {
            User u = User.builder()
                    .name("Alex Morgan")
                    .email("volunteer@voluntrack.com")
                    .password(passwordEncoder.encode("vol123"))
                    .role("VOLUNTEER")
                    .build();
            return userRepository.save(u);
        });

        if (!volunteerProfileRepository.findByUserId(volUser.getId()).isPresent()) {
            VolunteerProfile vp = VolunteerProfile.builder()
                    .userId(volUser.getId())
                    .skills("Environmental Cleanup, Emergency Relief")
                    .location("San Francisco, CA")
                    .availability("Weekends")
                    .totalHoursLogged(48)
                    .impactPoints(150)
                    .badgeTier("Gold")
                    .build();
            volunteerProfileRepository.save(vp);
        }

        // 4. Seed Sample Donor User
        User donorUser = userRepository.findByEmail("donor@voluntrack.com").orElseGet(() -> {
            User u = User.builder()
                    .name("Samantha Reed")
                    .email("donor@voluntrack.com")
                    .password(passwordEncoder.encode("donor123"))
                    .role("DONOR")
                    .build();
            return userRepository.save(u);
        });

        // 5. Seed Sample Events if none exist
        if (eventRepository.count() == 0) {
            Event e1 = Event.builder()
                    .charityId(charityUser.getId())
                    .title("Coastal Ocean Beach Cleanup")
                    .type("Environment")
                    .description("Join our community initiative to remove plastic waste and protect marine ecosystems along the coastal shore.")
                    .eventDate(LocalDate.now().plusDays(10))
                    .location("Pier 4, Santa Monica, CA")
                    .volunteersNeeded(30)
                    .volunteersAccepted(18)
                    .targetAmount(new BigDecimal("5000.00"))
                    .collectedAmount(new BigDecimal("3450.00"))
                    .status("UPCOMING")
                    .build();

            Event e2 = Event.builder()
                    .charityId(charityUser.getId())
                    .title("Community Urban Tree Plantation")
                    .type("Sustainability")
                    .description("Planting 500 indigenous trees across urban parks to improve air quality and green canopy coverage.")
                    .eventDate(LocalDate.now().plusDays(20))
                    .location("Central Community Park, NY")
                    .volunteersNeeded(45)
                    .volunteersAccepted(32)
                    .targetAmount(new BigDecimal("8000.00"))
                    .collectedAmount(new BigDecimal("6200.00"))
                    .status("ONGOING")
                    .build();

            Event e3 = Event.builder()
                    .charityId(charityUser.getId())
                    .title("Emergency Disaster Food Relief Drive")
                    .type("Relief")
                    .description("Distributing essential meal packages, clean water, and hygiene supplies to flood-affected families.")
                    .eventDate(LocalDate.now().minusDays(5))
                    .location("Eastside Relief Center")
                    .volunteersNeeded(20)
                    .volunteersAccepted(20)
                    .targetAmount(new BigDecimal("12000.00"))
                    .collectedAmount(new BigDecimal("12000.00"))
                    .status("COMPLETED")
                    .build();

            eventRepository.save(e1);
            eventRepository.save(e2);
            eventRepository.save(e3);

            // Seed Sample Attendance & Donation
            Attendance att = Attendance.builder()
                    .eventId(e1.getId())
                    .volunteerId(volUser.getId())
                    .hoursWorked(8)
                    .verified(true)
                    .build();
            attendanceRepository.save(att);

            EventDonation don = EventDonation.builder()
                    .eventId(e1.getId())
                    .donorId(donorUser.getId())
                    .amount(new BigDecimal("500.00"))
                    .paymentStatus("SUCCESS")
                    .transactionDate(LocalDateTime.now())
                    .build();
            eventDonationRepository.save(don);
        }

        System.out.println("=======================================================");
        System.out.println("🚀 VolunTrack Platform Seed Data Initialized Successfully!");
        System.out.println("Admin: admin@voluntrack.com / admin123");
        System.out.println("Charity: charity@voluntrack.com / charity123");
        System.out.println("Volunteer: volunteer@voluntrack.com / vol123");
        System.out.println("Donor: donor@voluntrack.com / donor123");
        System.out.println("=======================================================");
    }
}

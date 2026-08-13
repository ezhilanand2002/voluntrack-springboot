package com.voluntrack.service;

import com.voluntrack.dto.landing.*;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class LandingDataService {

    public List<NavLinkDTO> getNavLinks() {
        return Arrays.asList(
                new NavLinkDTO("Home", "/index.html"),
                new NavLinkDTO("Explore Events", "/events.html"),
                new NavLinkDTO("Analytics", "/dashboard.html"),
                new NavLinkDTO("Methodology", "/methodology.html"),
                new NavLinkDTO("Our Team", "/team.html")
        );
    }

    public List<CoreCapabilityDTO> getCoreCapabilities() {
        return Arrays.asList(
                CoreCapabilityDTO.builder()
                        .title("Volunteer Management")
                        .description("Apply for verified community events, log attendance hours seamlessly, and unlock Bronze to Platinum badge tiers as your impact grows.")
                        .icon("")
                        .proofLogos(Arrays.asList("Verified Shift Audit", "Automated Hour Log"))
                        .build(),
                CoreCapabilityDTO.builder()
                        .title("Fundraising & Donations")
                        .description("Support environmental, educational, and disaster relief campaigns with real-time target progress tracking and isolated simulated payments.")
                        .icon("")
                        .proofLogos(Arrays.asList("Real-Time Goal Meters", "Secure Payment Pipeline"))
                        .build(),
                CoreCapabilityDTO.builder()
                        .title("Instant PDF Certificates")
                        .description("Generate and download officially verified digital volunteer certificates directly in your browser powered by client-side jsPDF.")
                        .icon("")
                        .proofLogos(Arrays.asList("CryptSig Standard", "jsPDF Engine"))
                        .build(),
                CoreCapabilityDTO.builder()
                        .title("Impact Analytics & Reports")
                        .description("Real-time charity performance reports, volunteer engagement metrics, and interactive Chart.js visualizations for complete transparency.")
                        .icon("")
                        .proofLogos(Arrays.asList("Chart.js Data", "RealTime Analytics"))
                        .build()
        );
    }

    public List<StatCalloutDTO> getPlatformStats() {
        return Arrays.asList(
                new StatCalloutDTO("12,450+", "Hours Logged"),
                new StatCalloutDTO("$850,000+", "Funds Raised"),
                new StatCalloutDTO("4,890+", "Certificates Issued"),
                new StatCalloutDTO("150+", "Verified Charities"),
                new StatCalloutDTO("98%", "Shift Fulfillment")
        );
    }

    public List<StatCalloutDTO> getStats() {
        return getPlatformStats();
    }

    public List<TeamMemberDTO> getTeamMembers() {
        return Arrays.asList(
                new TeamMemberDTO("Anand", "Head of Community & Impact", "Oversees NGO outreach & community volunteer retention strategies.", ""),
                new TeamMemberDTO("Bala", "Lead Platform Architect", "Architects secure Spring Boot backend microservices and JWT security.", ""),
                new TeamMemberDTO("Surya", "Director of NGO Partnerships", "Fosters relationships with 150+ international charity organizations.", "")
        );
    }

    public List<MethodStepDTO> getMethodologySteps() {
        return Arrays.asList(
                MethodStepDTO.builder()
                        .stepNumber("STEP 01")
                        .title("Discover & Apply")
                        .icon("")
                        .bulletPoints(Arrays.asList(
                                "Filter verified NGO community campaigns",
                                "Match opportunities by skills & availability",
                                "One-click application submission with instant routing"
                        ))
                        .build(),
                MethodStepDTO.builder()
                        .stepNumber("STEP 02")
                        .title("Participate & Log Hours")
                        .icon("")
                        .bulletPoints(Arrays.asList(
                                "Check in on event day via mobile dashboard",
                                "Supervisor validation of logged service hours",
                                "Automatic impact points calculation"
                        ))
                        .build(),
                MethodStepDTO.builder()
                        .stepNumber("STEP 03")
                        .title("Certify & Celebrate")
                        .icon("")
                        .bulletPoints(Arrays.asList(
                                "Unlock official volunteer badge progression",
                                "Instant in-browser PDF certificate rendering",
                                "Transparent financial donation auditing"
                        ))
                        .build()
        );
    }

    public List<MethodStepDTO> getMethodSteps() {
        return getMethodologySteps();
    }

    public List<PartnerLogoDTO> getPartnerLogos() {
        return Arrays.asList(
                new PartnerLogoDTO("Global Hope Foundation", ""),
                new PartnerLogoDTO("EcoEarth Alliance", ""),
                new PartnerLogoDTO("RedCross International", ""),
                new PartnerLogoDTO("UNICEF Partner Network", ""),
                new PartnerLogoDTO("Habitat Action Group", ""),
                new PartnerLogoDTO("CleanOceans Society", "")
        );
    }

    public List<TestimonialDTO> getTestimonials() {
        return Arrays.asList(
                new TestimonialDTO("VolunTrack transformed how we manage our 500+ volunteer shifts monthly. The instant PDF certificate issuance is a huge time-saver!", "Dr. Elena Rostova", "Executive Director, EcoEarth Alliance", "", "https://linkedin.com"),
                new TestimonialDTO("As a donor, seeing real-time campaign progress bars gives me 100% confidence that my contributions reach local communities.", "Marcus Vance", "Lead Donor & Civic Angel", "", "https://linkedin.com"),
                new TestimonialDTO("Earning my Gold Badge on VolunTrack helped me showcase verified community service hours directly on my professional portfolio.", "Priya Sharma", "Senior Volunteer Coordinator", "", "https://linkedin.com")
        );
    }
}

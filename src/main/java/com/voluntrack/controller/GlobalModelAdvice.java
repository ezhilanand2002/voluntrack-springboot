package com.voluntrack.controller;

import com.voluntrack.dto.landing.NavLinkDTO;
import com.voluntrack.dto.landing.PartnerLogoDTO;
import com.voluntrack.service.LandingDataService;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@ControllerAdvice
public class GlobalModelAdvice {

    private final LandingDataService landingDataService;

    public GlobalModelAdvice(LandingDataService landingDataService) {
        this.landingDataService = landingDataService;
    }

    @ModelAttribute("navLinks")
    public List<NavLinkDTO> getNavLinks() {
        return landingDataService.getNavLinks();
    }

    @ModelAttribute("partnerLogos")
    public List<PartnerLogoDTO> getPartnerLogos() {
        return landingDataService.getPartnerLogos();
    }
}

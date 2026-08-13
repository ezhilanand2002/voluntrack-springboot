package com.voluntrack.controller;

import com.voluntrack.service.LandingDataService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebViewController {

    private final LandingDataService landingDataService;

    public WebViewController(LandingDataService landingDataService) {
        this.landingDataService = landingDataService;
    }

    @GetMapping({"/", "/index.html"})
    public String index(Model model) {
        model.addAttribute("coreItems", landingDataService.getCoreCapabilities());
        model.addAttribute("stats", landingDataService.getStats());
        model.addAttribute("teamMembers", landingDataService.getTeamMembers());
        model.addAttribute("methodSteps", landingDataService.getMethodSteps());
        model.addAttribute("testimonials", landingDataService.getTestimonials());
        return "index";
    }

    @GetMapping("/events.html")
    public String events() {
        return "events";
    }

    @GetMapping("/event-detail.html")
    public String eventDetail() {
        return "event-detail";
    }

    @GetMapping("/methodology.html")
    public String methodology(Model model) {
        model.addAttribute("methodSteps", landingDataService.getMethodSteps());
        model.addAttribute("stats", landingDataService.getStats());
        model.addAttribute("coreItems", landingDataService.getCoreCapabilities());
        return "methodology";
    }

    @GetMapping("/team.html")
    public String team(Model model) {
        model.addAttribute("teamMembers", landingDataService.getTeamMembers());
        return "team";
    }

    @GetMapping("/dashboard.html")
    public String dashboard() {
        return "dashboard";
    }

    @GetMapping("/login.html")
    public String login() {
        return "login";
    }

    @GetMapping("/register.html")
    public String register() {
        return "register";
    }

    @GetMapping("/admin-dashboard.html")
    public String adminDashboard() {
        return "admin-dashboard";
    }

    @GetMapping("/charity-dashboard.html")
    public String charityDashboard() {
        return "charity-dashboard";
    }

    @GetMapping("/volunteer-dashboard.html")
    public String volunteerDashboard() {
        return "volunteer-dashboard";
    }

    @GetMapping("/donor-dashboard.html")
    public String donorDashboard() {
        return "donor-dashboard";
    }
}

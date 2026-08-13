package com.voluntrack.controller;

import com.voluntrack.dto.CharityReportDTO;
import com.voluntrack.service.ReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reports")
@CrossOrigin(origins = "*")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/charity/{charityId}")
    public ResponseEntity<CharityReportDTO> getCharityReport(@PathVariable Long charityId) {
        return ResponseEntity.ok(reportService.getCharityReport(charityId));
    }
}

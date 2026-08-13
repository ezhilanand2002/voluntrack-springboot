package com.voluntrack.service;

import com.voluntrack.entity.Event;
import com.voluntrack.entity.EventDonation;
import com.voluntrack.repository.EventDonationRepository;
import com.voluntrack.repository.EventRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class DonationService {

    private final EventDonationRepository donationRepository;
    private final EventRepository eventRepository;

    public DonationService(EventDonationRepository donationRepository, EventRepository eventRepository) {
        this.donationRepository = donationRepository;
        this.eventRepository = eventRepository;
    }

    /**
     * SIMULATED PAYMENT METHOD:
     * This method simulates a payment gateway transaction (Razorpay / Stripe).
     * It immediately marks the donation as successful and updates the Event's collectedAmount.
     * To integrate real payment gateways later, replace this method's body with gateway webhook/verification logic.
     */
    @Transactional
    public EventDonation processSimulatedPayment(Long eventId, Long donorId, BigDecimal amount) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new IllegalArgumentException("Event not found with ID: " + eventId));

        // Create Donation Record
        EventDonation donation = EventDonation.builder()
                .eventId(eventId)
                .donorId(donorId)
                .amount(amount)
                .paymentStatus("SUCCESS") // Simulated Instant Success
                .build();

        donation = donationRepository.save(donation);

        // Instantly Update Event's collectedAmount
        BigDecimal currentCollected = event.getCollectedAmount() != null ? event.getCollectedAmount() : BigDecimal.ZERO;
        event.setCollectedAmount(currentCollected.add(amount));
        eventRepository.save(event);

        return donation;
    }

    public List<EventDonation> getDonationsForEvent(Long eventId) {
        return donationRepository.findByEventId(eventId);
    }

    public List<EventDonation> getDonationsByDonor(Long donorId) {
        return donationRepository.findByDonorId(donorId);
    }
}

package com.voluntrack.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "event_donations")
public class EventDonation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long eventId;

    @Column(nullable = false)
    private Long donorId;

    @Column(nullable = false)
    private BigDecimal amount;

    private String paymentStatus = "SUCCESS";
    private LocalDateTime transactionDate;

    public EventDonation() {}

    public EventDonation(Long id, Long eventId, Long donorId, BigDecimal amount, String paymentStatus, LocalDateTime transactionDate) {
        this.id = id;
        this.eventId = eventId;
        this.donorId = donorId;
        this.amount = amount;
        this.paymentStatus = paymentStatus != null ? paymentStatus : "SUCCESS";
        this.transactionDate = transactionDate != null ? transactionDate : LocalDateTime.now();
    }

    @PrePersist
    protected void onCreate() {
        if (this.transactionDate == null) {
            this.transactionDate = LocalDateTime.now();
        }
    }

    public static EventDonationBuilder builder() {
        return new EventDonationBuilder();
    }

    public static class EventDonationBuilder {
        private Long id;
        private Long eventId;
        private Long donorId;
        private BigDecimal amount;
        private String paymentStatus = "SUCCESS";
        private LocalDateTime transactionDate;

        public EventDonationBuilder id(Long id) { this.id = id; return this; }
        public EventDonationBuilder eventId(Long eventId) { this.eventId = eventId; return this; }
        public EventDonationBuilder donorId(Long donorId) { this.donorId = donorId; return this; }
        public EventDonationBuilder amount(BigDecimal amount) { this.amount = amount; return this; }
        public EventDonationBuilder paymentStatus(String paymentStatus) { this.paymentStatus = paymentStatus; return this; }
        public EventDonationBuilder transactionDate(LocalDateTime transactionDate) { this.transactionDate = transactionDate; return this; }

        public EventDonation build() {
            return new EventDonation(id, eventId, donorId, amount, paymentStatus, transactionDate);
        }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getEventId() { return eventId; }
    public void setEventId(Long eventId) { this.eventId = eventId; }

    public Long getDonorId() { return donorId; }
    public void setDonorId(Long donorId) { this.donorId = donorId; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public String getPaymentStatus() { return paymentStatus; }
    public void setPaymentStatus(String paymentStatus) { this.paymentStatus = paymentStatus; }

    public LocalDateTime getTransactionDate() { return transactionDate; }
    public void setTransactionDate(LocalDateTime transactionDate) { this.transactionDate = transactionDate; }
}

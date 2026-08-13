package com.voluntrack.dto;

import java.math.BigDecimal;

public class DonationDTO {
    private BigDecimal amount;
    private String paymentMethod;

    public DonationDTO() {}

    public DonationDTO(BigDecimal amount, String paymentMethod) {
        this.amount = amount;
        this.paymentMethod = paymentMethod;
    }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
}

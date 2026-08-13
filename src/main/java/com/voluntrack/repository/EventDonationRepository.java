package com.voluntrack.repository;

import com.voluntrack.entity.EventDonation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventDonationRepository extends JpaRepository<EventDonation, Long> {
    List<EventDonation> findByEventId(Long eventId);
    List<EventDonation> findByDonorId(Long donorId);
}

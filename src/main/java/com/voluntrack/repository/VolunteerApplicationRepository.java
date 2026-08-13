package com.voluntrack.repository;

import com.voluntrack.entity.VolunteerApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VolunteerApplicationRepository extends JpaRepository<VolunteerApplication, Long> {
    List<VolunteerApplication> findByEventId(Long eventId);
    List<VolunteerApplication> findByVolunteerId(Long volunteerId);
    Optional<VolunteerApplication> findByEventIdAndVolunteerId(Long eventId, Long volunteerId);
}

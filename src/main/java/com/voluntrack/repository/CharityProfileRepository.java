package com.voluntrack.repository;

import com.voluntrack.entity.CharityProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CharityProfileRepository extends JpaRepository<CharityProfile, Long> {
    Optional<CharityProfile> findByUserId(Long userId);
}

package com.voluntrack.repository;

import com.voluntrack.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    @Query("SELECT e FROM Event e WHERE (:keyword IS NULL OR LOWER(e.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(e.location) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(e.type) LIKE LOWER(CONCAT('%', :keyword, '%')))")
    List<Event> searchEvents(@Param("keyword") String keyword);

    List<Event> findByCharityId(Long charityId);
}

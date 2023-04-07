package com.system.hostel_booking_system.repo;

import com.system.hostel_booking_system.entity.TripleSeater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TripleSeaterRepo extends JpaRepository<TripleSeater, Integer> {
    @Query(value = "select COUNT(*) from Triple_Seater", nativeQuery = true)
    Long countAllRows();
}

package com.system.hostel_booking_system.repo;

import com.system.hostel_booking_system.entity.DoubleSeater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DoubleSeaterRepo extends JpaRepository<DoubleSeater, Integer> {
    @Query(value = "select COUNT(*) from Double_Seater", nativeQuery = true)
    Long countAllRows();
}

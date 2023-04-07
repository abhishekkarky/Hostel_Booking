package com.system.hostel_booking_system.repo;

import com.system.hostel_booking_system.entity.FourSeater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FourSeaterRepo extends JpaRepository<FourSeater, Integer> {
    @Query(value = "select COUNT(*) from Four_Seater", nativeQuery = true)
    Long countAllRows();
}

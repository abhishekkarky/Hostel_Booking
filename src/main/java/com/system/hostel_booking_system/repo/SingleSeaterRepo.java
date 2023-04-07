package com.system.hostel_booking_system.repo;

import com.system.hostel_booking_system.entity.SingleSeater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SingleSeaterRepo extends JpaRepository<SingleSeater, Integer> {
    @Query(value = "select COUNT(*) from Single_Seater", nativeQuery = true)
    Long countAllRows();
}

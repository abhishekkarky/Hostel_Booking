package com.system.hostel_booking_system.repo;

import com.system.hostel_booking_system.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepo extends JpaRepository<Booking, Integer> {

    @Query(value = "select COUNT(*) from Booking", nativeQuery = true)
    Long countAllRows();
}

package com.system.hostel_booking_system.repo;

import com.system.hostel_booking_system.entity.DoubleSeater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DoubleSeaterRepo extends JpaRepository<DoubleSeater, Integer> {
    @Query(value = "select COUNT(*) from Double_Seater", nativeQuery = true)
    Long countAllRows();

    @Query(value = "SELECT * FROM Double_Seater order by id desc limit 3", nativeQuery = true)
    Optional<List<DoubleSeater>> findMostRecent();

    @Query(value = "SELECT * FROM Double_Seater WHERE id = ?1", nativeQuery = true)
    Optional<List<DoubleSeater>> findAllByLocation(Integer id);
}

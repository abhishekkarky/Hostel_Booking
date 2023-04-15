package com.system.hostel_booking_system.repo;

import com.system.hostel_booking_system.entity.FourSeater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FourSeaterRepo extends JpaRepository<FourSeater, Integer> {
    @Query(value = "select COUNT(*) from Four_Seater", nativeQuery = true)
    Long countAllRows();

    @Query(value = "SELECT * FROM Four_Seater order by id desc limit 3", nativeQuery = true)
    Optional<List<FourSeater>> findMostRecent();

    @Query(value = "SELECT * FROM Four_Seater WHERE id = ?1", nativeQuery = true)
    Optional<List<FourSeater>> findAllByLocation(Integer id);

    @Query(value = "SELECT * FROM Four_Seater ORDER BY hostel_price ASC", nativeQuery = true)
    Optional<List<FourSeater>> findAllBySortedPrice();
}

package com.system.hostel_booking_system.repo;

import com.system.hostel_booking_system.entity.Blogs;
import com.system.hostel_booking_system.entity.SingleSeater;
import com.system.hostel_booking_system.entity.TripleSeater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TripleSeaterRepo extends JpaRepository<TripleSeater, Integer> {
    @Query(value = "select COUNT(*) from Triple_Seater", nativeQuery = true)
    Long countAllRows();

    @Query(value = "SELECT * FROM Triple_Seater order by id desc limit 3", nativeQuery = true)
    Optional<List<TripleSeater>> findMostRecent();

    @Query(value = "SELECT * FROM Triple_Seater WHERE id = ?1", nativeQuery = true)
    Optional<List<TripleSeater>> findAllByLocation(Integer id);
}

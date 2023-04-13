package com.system.hostel_booking_system.repo;

import com.system.hostel_booking_system.entity.Blogs;
import com.system.hostel_booking_system.entity.SingleSeater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SingleSeaterRepo extends JpaRepository<SingleSeater, Integer> {
    @Query(value = "select COUNT(*) from Single_Seater", nativeQuery = true)
    Long countAllRows();

    @Query(value = "SELECT * FROM Single_Seater order by id desc limit 3", nativeQuery = true)
    Optional<List<SingleSeater>> findMostRecent();
    @Query(value = "SELECT * FROM Single_Seater WHERE id = ?1", nativeQuery = true)
    Optional<List<SingleSeater>> findAllByLocation(Integer id);
}

package com.system.hostel_booking_system.repo;

import com.system.hostel_booking_system.entity.Blogs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BlogsRepo extends JpaRepository<Blogs, Integer> {
    @Query(value = "select COUNT(*) from Blogs", nativeQuery = true)
    Long countAllRows();

    @Query(value = "SELECT * FROM Blogs order by id desc limit 3", nativeQuery = true)
    Optional<List<Blogs>> findMostRecent();
}

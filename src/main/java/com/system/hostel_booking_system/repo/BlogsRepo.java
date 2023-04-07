package com.system.hostel_booking_system.repo;

import com.system.hostel_booking_system.entity.Blogs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogsRepo extends JpaRepository<Blogs, Integer> {
    @Query(value = "select COUNT(*) from Blogs", nativeQuery = true)
    Long countAllRows();
}

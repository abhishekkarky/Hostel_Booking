package com.system.hostel_booking_system.repo;

import com.system.hostel_booking_system.entity.Queries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QueriesRepo extends JpaRepository<Queries, Integer> {

}
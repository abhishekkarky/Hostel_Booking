package com.system.hostel_booking_system.repo;

import com.system.hostel_booking_system.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    @Query(value = "select * from HS_admin where email=?1", nativeQuery = true)
    Optional<User> findByEmail(String email);
}

package com.system.hostel_booking_system.service.impl;

import com.system.hostel_booking_system.entity.User;
import com.system.hostel_booking_system.exception.AppException;
import com.system.hostel_booking_system.pojo.UserPojo;
import com.system.hostel_booking_system.repo.UserRepo;
import com.system.hostel_booking_system.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    @Override
    public UserPojo findByEmail(String email) {
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new AppException("Invalid User email", HttpStatus.BAD_REQUEST));
        return new UserPojo(user);
    }
}

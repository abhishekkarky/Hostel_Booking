package com.system.hostel_booking_system.service;

import com.system.hostel_booking_system.pojo.UserPojo;

public interface UserService {
    UserPojo findByEmail(String email);
}

package com.system.hostel_booking_system.service;

import com.system.hostel_booking_system.entity.Booking;
import com.system.hostel_booking_system.pojo.BookingPojo;

import java.util.List;

public interface BookingService {
    String save(BookingPojo bookingPojo);

    List<Booking> fetchAll();

    void deleteById(Integer id);
}

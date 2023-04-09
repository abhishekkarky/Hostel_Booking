package com.system.hostel_booking_system.service.impl;

import com.system.hostel_booking_system.repo.BookingRepo;
import com.system.hostel_booking_system.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final BookingRepo bookingRepo;
    public Long countRows() {
        return bookingRepo.countAllRows();
    }

}

package com.system.hostel_booking_system.service.impl;

import com.system.hostel_booking_system.entity.Booking;
import com.system.hostel_booking_system.pojo.BookingPojo;
import com.system.hostel_booking_system.repo.BookingRepo;
import com.system.hostel_booking_system.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final BookingRepo bookingRepo;
    public Long countRows() {
        return bookingRepo.countAllRows();
    }

    @Override
    public String save(BookingPojo bookingPojo) {
        Booking booking=new Booking();
        if(bookingPojo.getId()!=null){
            booking.setId(bookingPojo.getId());
        }
        booking.setHostel_name(bookingPojo.getHostel_name());
        booking.setHostel_location(bookingPojo.getHostel_location());
        booking.setName(bookingPojo.getName());
        booking.setPhone(bookingPojo.getPhone());
        booking.setEmail(bookingPojo.getEmail());
        booking.setRoom_type(bookingPojo.getRoom_type());
        booking.setCheckin(bookingPojo.getCheckin());
        bookingRepo.save(booking);
        return null;
    }

    @Override
    public List<Booking> fetchAll() {
        return this.bookingRepo.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        bookingRepo.deleteById(id);
    }
}

package com.system.hostel_booking_system.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/192.168.1.1.1")
public class AdminController {

    @GetMapping("")
    public String getAdminDashboardPage() {
        return "admin-dashboard";
    }

    @GetMapping("/booking-list")
    public String getAdminBookingListPage() {
        return "booking-list";
    }

    @GetMapping("/single-seater-rooms-list")
    public String getSingleSeaterRoomsListPage() {
        return "single-room-list";
    }

    @GetMapping("/double-seater-rooms-list")
    public String getDoubleSeaterRoomsListPage() {
        return "double-room-list";
    }

    @GetMapping("/triple-seater-rooms-list")
    public String getTripleSeaterRoomsListPage() {
        return "triple-room-list";
    }

    @GetMapping("/four-seater-rooms-list")
    public String getFourSeaterRoomsListPage() {
        return "four-room-list";
    }

    @GetMapping("/blogs-list")
    public String getBlogsListPage() {
        return "blogs-list";
    }

    @GetMapping("/add-single-seater-room")
    public String getAddSingleSeaterRoomPage() {
        return "add-single-seater-room";
    }

    @GetMapping("/add-double-seater-room")
    public String getAddDoubleSeaterRoomPage() {
        return "add-double-seater-room";
    }

    @GetMapping("/add-triple-seater-room")
    public String getAddTripleSeaterRoomPage() {
        return "add-triple-seater-room";
    }

    @GetMapping("/add-four-seater-room")
    public String getAddFourSeaterRoomPage() {
        return "add-four-seater-room";
    }

    @GetMapping("/add-blogs")
    public String getAddBlogsPage() {
        return "add-blog";
    }
}

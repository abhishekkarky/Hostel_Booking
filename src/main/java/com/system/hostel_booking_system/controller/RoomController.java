package com.system.hostel_booking_system.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/room")
public class RoomController {

    @GetMapping("/single-seater")
    public String getSingleSeater() {
        return "single-seater";
    }

    @GetMapping("/room-description")
        public String getRoomDescription() {
        return "room-description";
    }

}

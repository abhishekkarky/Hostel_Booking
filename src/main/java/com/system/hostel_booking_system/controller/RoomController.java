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

    @GetMapping("/double-seater")
    public String getDoubleSeater() {
        return "double-seater";
    }

    @GetMapping("/triple-seater")
    public String getTripleSeater() {
        return "triple-seater";
    }

    @GetMapping("/four-seater")
    public String getFourSeater() {
        return "four-seater";
    }


    @GetMapping("/description")
        public String getRoomDescription() {
        return "room-description";
    }

}

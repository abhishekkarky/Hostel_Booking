package com.system.hostel_booking_system.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/landing")
public class LandingController {
    @GetMapping("")
    public String getLandingPage() {
        return "index";
    }
}

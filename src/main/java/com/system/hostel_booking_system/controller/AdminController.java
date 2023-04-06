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
}

package com.system.hostel_booking_system.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping()
public class AdminLoginController {
    @GetMapping("/192.168.0.1.1")
    public String showLoginPage(Model model, @RequestParam(value = "error", required = false) String error) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (error != null) {
            model.addAttribute("errorMsg", "Invalid email or password!!!");
        }
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "admin-login";
        }
        return "redirect:/192.168.1.1.1";
    }
    @GetMapping("/logout")
    public String logout(Authentication authentication, Model model) {
        if (authentication.isAuthenticated()) {
            SecurityContextHolder.clearContext();
        }
        model.addAttribute("message", "Logged Out Successfully!!!");
        return "/admin-login";
    }
}

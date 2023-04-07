package com.system.hostel_booking_system.controller;

import com.system.hostel_booking_system.entity.Blogs;
import com.system.hostel_booking_system.entity.SingleSeater;
import com.system.hostel_booking_system.service.BlogsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("")
public class LandingController {
    private final BlogsService blogsService;

    @GetMapping("/landing")
    public String getLandingPage(Authentication authentication) {
        if (authentication!=null){
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            for (GrantedAuthority grantedAuthority : authorities) {
                if (grantedAuthority.getAuthority().equals("Admin")) {
                    return "redirect:/192.168.1.1.1";
                }
            }
        }
        return "index";
    }

    @GetMapping("/contact")
    public String getContactPage() {
        return "contact";
    }

    @GetMapping("/blogs")
    public String getBlogs(Model model) {
        List<Blogs> blogs = blogsService.fetchAll();
        model.addAttribute("blogs", blogs);
        return "blogs";
    }

    @GetMapping("/services")
    public String getServices() {
        return "services";
    }

    @GetMapping("/about")
    public String getAboutPage() {
        return "about";
    }


}

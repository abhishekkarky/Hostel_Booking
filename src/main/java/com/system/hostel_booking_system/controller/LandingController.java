package com.system.hostel_booking_system.controller;

import com.system.hostel_booking_system.entity.Blogs;
import com.system.hostel_booking_system.entity.SingleSeater;
import com.system.hostel_booking_system.pojo.QueriesPojo;
import com.system.hostel_booking_system.service.BlogsService;
import com.system.hostel_booking_system.service.QueryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("")
public class LandingController {
    private final BlogsService blogsService;
    private final QueryService queryService;

    @GetMapping("/landing")
    public String getLandingPage(Authentication authentication, Model model) {
        if (authentication!=null){
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            for (GrantedAuthority grantedAuthority : authorities) {
                if (grantedAuthority.getAuthority().equals("Admin")) {
                    return "redirect:/192.168.1.1.1";
                }
            }
        }
        List<Blogs> mostRecentBlogs = blogsService.fetchMostRecent();
        model.addAttribute("mostRecentBlogs", mostRecentBlogs);
        return "index";
    }

    @GetMapping("/contact")
    public String getContactPage(Model model) {
        model.addAttribute("queries", new QueriesPojo());
        return "contact";
    }
    @PostMapping("/saveQueries")
    public String saveContact(@Valid QueriesPojo queriesPojo) {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setTo(queriesPojo.getEmail());
//        message.setSubject("Hostellers Nepal");
//        message.setText("Hello there pretty stranger!!! Thank you for contacting us. We will contact you back ASAP.");
//        getJavaMailSender.send(message);
        queryService.save(queriesPojo);
        return "redirect:/landing";
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

package com.system.hostel_booking_system.controller;

import com.system.hostel_booking_system.entity.SingleSeater;
import com.system.hostel_booking_system.pojo.*;
import com.system.hostel_booking_system.service.*;
import com.system.hostel_booking_system.service.impl.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/192.168.1.1.1")
public class AdminController {

    private final SingleSeaterService singleSeaterService;
    private final DoubleSeaterService doubleSeaterService;
    private final TripleSeaterService tripleSeaterService;
    private final FourSeaterService fourSeaterService;
    private final BlogsService blogsService;
    private final SingleSeaterServiceImpl singleSeaterServiceImpl;
    private final DoubleSeaterServiceImpl doubleSeaterServiceImpl;
    private final TripleSeaterServiceImpl tripleSeaterServiceImpl;
    private final FourSeaterServiceImpl fourSeaterServiceImpl;
    private final BlogsServiceImpl blogsServiceImpl;





    @GetMapping("")
    public String getAdminDashboardPage(Model model) {
        Long singleSeater = singleSeaterServiceImpl.countRows();
        model.addAttribute("countSS",singleSeater);

        Long doubleSeater = doubleSeaterServiceImpl.countRows();
        model.addAttribute("countDS",doubleSeater);

        Long tripleSeater = tripleSeaterServiceImpl.countRows();
        model.addAttribute("countTS",tripleSeater);

        Long fourSeater = fourSeaterServiceImpl.countRows();
        model.addAttribute("countFS",fourSeater);

        Long blogs = blogsServiceImpl.countRows();
        model.addAttribute("countBlogs",blogs);

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
    public String getAddSingleSeaterRoomPage(Model model) {
        model.addAttribute("ss", new SingleSeaterPojo());
        return "add-single-seater-room";
    }
    @PostMapping("/save/single-seater-room")
    public String saveSingleSeaterRoom(@Valid SingleSeaterPojo singleSeaterPojo, Model model, Principal principal) throws IOException {
        singleSeaterService.save(singleSeaterPojo);
        return "redirect:/192.168.1.1.1";
    }

    @GetMapping("/add-double-seater-room")
    public String getAddDoubleSeaterRoomPage(Model model) {
        model.addAttribute("ds", new DoubleSeaterPojo());
        return "add-double-seater-room";
    }
    @PostMapping("/save/double-seater-room")
    public String saveDoubleSeaterRoom(@Valid DoubleSeaterPojo doubleSeaterPojo, Model model, Principal principal) throws IOException {
        doubleSeaterService.save(doubleSeaterPojo);
        return "redirect:/192.168.1.1.1";
    }

    @GetMapping("/add-triple-seater-room")
    public String getAddTripleSeaterRoomPage(Model model) {
        model.addAttribute("ts", new TripleSeaterPojo());
        return "add-triple-seater-room";
    }
    @PostMapping("/save/triple-seater-room")
    public String saveTripleSeaterRoom(@Valid TripleSeaterPojo tripleSeaterPojo, Model model, Principal principal) throws IOException {
        tripleSeaterService.save(tripleSeaterPojo);
        return "redirect:/192.168.1.1.1";
    }

    @GetMapping("/add-four-seater-room")
    public String getAddFourSeaterRoomPage(Model model) {
        model.addAttribute("fs", new FourSeaterPojo());
        return "add-four-seater-room";
    }
    @PostMapping("/save/four-seater-room")
    public String saveFourSeaterRoom(@Valid FourSeaterPojo fourSeaterPojo, Model model, Principal principal) throws IOException {
        fourSeaterService.save(fourSeaterPojo);
        return "redirect:/192.168.1.1.1";
    }

    @GetMapping("/add-blogs")
    public String getAddBlogsPage(Model model) {
        model.addAttribute("blogs", new BlogsPojo());
        return "add-blog";
    }
    @PostMapping("/save/blogs")
    public String saveBlogs(@Valid BlogsPojo blogsPojo, Model model, Principal principal) throws IOException {
        blogsService.save(blogsPojo);
        return "redirect:/192.168.1.1.1";
    }
}

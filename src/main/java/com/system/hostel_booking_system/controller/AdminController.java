package com.system.hostel_booking_system.controller;
import com.system.hostel_booking_system.entity.*;
import com.system.hostel_booking_system.pojo.*;
import com.system.hostel_booking_system.service.*;
import com.system.hostel_booking_system.service.impl.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.List;

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
    private final QueryService queryService;

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
    public String getSingleSeaterRoomsListPage(Model model) {
        List<SingleSeater> singleSeaters = singleSeaterService.fetchAll();
        model.addAttribute("ss", singleSeaters);
        return "single-room-list";
    }
    @GetMapping("/editSingleSeater/{id}")
    public String editSingleSeater(@PathVariable("id") Integer id, Model model) {
        SingleSeater singleSeater = singleSeaterService.fetchById(id);
        model.addAttribute("ss", new SingleSeaterPojo(singleSeater));
        return "add-single-seater-room";
    }
    @GetMapping("/deleteSingleSeater/{id}")
    public String deleteSingleSeater(@PathVariable("id") Integer id) {
        singleSeaterService.deleteById(id);
        return "redirect:/192.168.1.1.1/single-seater-rooms-list";
    }

    @GetMapping("/double-seater-rooms-list")
    public String getDoubleSeaterRoomsListPage(Model model) {
        List<DoubleSeater> doubleSeaters = doubleSeaterService.fetchAll();
        model.addAttribute("ds", doubleSeaters);
        return "double-room-list";
    }
    @GetMapping("/editDoubleSeater/{id}")
    public String editDoubleSeater(@PathVariable("id") Integer id, Model model) {
        DoubleSeater doubleSeater = doubleSeaterService.fetchById(id);
        model.addAttribute("ds", new DoubleSeaterPojo(doubleSeater));
        return "add-double-seater-room";
    }
    @GetMapping("/deleteDoubleSeater/{id}")
    public String deleteDoubleSeater(@PathVariable("id") Integer id) {
        doubleSeaterService.deleteById(id);
        return "redirect:/192.168.1.1.1/double-seater-rooms-list";
    }

    @GetMapping("/triple-seater-rooms-list")
    public String getTripleSeaterRoomsListPage(Model model) {
        List<TripleSeater> tripleSeaters = tripleSeaterService.fetchAll();
        model.addAttribute("ts", tripleSeaters);
        return "triple-room-list";
    }
    @GetMapping("/editTripleSeater/{id}")
    public String editTripleSeater(@PathVariable("id") Integer id, Model model) {
        TripleSeater tripleSeater = tripleSeaterService.fetchById(id);
        model.addAttribute("ts", new TripleSeaterPojo(tripleSeater));
        return "add-triple-seater-room";
    }
    @GetMapping("/deleteTripleSeater/{id}")
    public String deleteTripleSeater(@PathVariable("id") Integer id) {
        tripleSeaterService.deleteById(id);
        return "redirect:/192.168.1.1.1/triple-seater-rooms-list";
    }

    @GetMapping("/four-seater-rooms-list")
    public String getFourSeaterRoomsListPage(Model model) {
        List<FourSeater> fourSeaters = fourSeaterService.fetchAll();
        model.addAttribute("fs", fourSeaters);
        return "four-room-list";
    }
    @GetMapping("/editFourSeater/{id}")
    public String editFourSeater(@PathVariable("id") Integer id, Model model) {
        FourSeater fourSeater = fourSeaterService.fetchById(id);
        model.addAttribute("fs", new FourSeaterPojo(fourSeater));
        return "add-four-seater-room";
    }
    @GetMapping("/deleteFourSeater/{id}")
    public String deleteFourSeater(@PathVariable("id") Integer id) {
        fourSeaterService.deleteById(id);
        return "redirect:/192.168.1.1.1/four-seater-rooms-list";
    }

    @GetMapping("/blogs-list")
    public String getBlogsListPage(Model model) {
        List<Blogs> blogs = blogsService.fetchAll();
        model.addAttribute("blogs", blogs);
        return "blogs-list";
    }
    @GetMapping("/editBlogs/{id}")
    public String editBlogs(@PathVariable("id") Integer id, Model model) {
        Blogs blogs = blogsService.fetchById(id);
        model.addAttribute("blogs", new BlogsPojo(blogs));
        return "add-blog";
    }
    @GetMapping("/deleteBlogs/{id}")
    public String deleteBlogs(@PathVariable("id") Integer id) {
        blogsService.deleteById(id);
        return "redirect:/192.168.1.1.1/blogs-list";
    }

    @GetMapping("/query")
    public String getQueryPage(Model model) {
        List<Queries> queries = queryService.fetchAll();
        model.addAttribute("queries", queries);
        return "query-section";
    }
    @GetMapping("/deleteQuery/{id}")
    public String deleteQuery(@PathVariable("id") Integer id) {
        queryService.deleteById(id);
        return "redirect:/192.168.1.1.1/query";
    }

    @GetMapping("/add-single-seater-room")
    public String getAddSingleSeaterRoomPage(Model model) {
        model.addAttribute("ss", new SingleSeaterPojo());
        return "add-single-seater-room";
    }
    @PostMapping("/save/single-seater-room")
    public String saveSingleSeaterRoom(@Valid SingleSeaterPojo singleSeaterPojo) throws IOException {
        singleSeaterService.save(singleSeaterPojo);
        return "redirect:/192.168.1.1.1";
    }

    @GetMapping("/add-double-seater-room")
    public String getAddDoubleSeaterRoomPage(Model model) {
        model.addAttribute("ds", new DoubleSeaterPojo());
        return "add-double-seater-room";
    }
    @PostMapping("/save/double-seater-room")
    public String saveDoubleSeaterRoom(@Valid DoubleSeaterPojo doubleSeaterPojo) throws IOException {
        doubleSeaterService.save(doubleSeaterPojo);
        return "redirect:/192.168.1.1.1";
    }

    @GetMapping("/add-triple-seater-room")
    public String getAddTripleSeaterRoomPage(Model model) {
        model.addAttribute("ts", new TripleSeaterPojo());
        return "add-triple-seater-room";
    }
    @PostMapping("/save/triple-seater-room")
    public String saveTripleSeaterRoom(@Valid TripleSeaterPojo tripleSeaterPojo) throws IOException {
        tripleSeaterService.save(tripleSeaterPojo);
        return "redirect:/192.168.1.1.1";
    }

    @GetMapping("/add-four-seater-room")
    public String getAddFourSeaterRoomPage(Model model) {
        model.addAttribute("fs", new FourSeaterPojo());
        return "add-four-seater-room";
    }
    @PostMapping("/save/four-seater-room")
    public String saveFourSeaterRoom(@Valid FourSeaterPojo fourSeaterPojo) throws IOException {
        fourSeaterService.save(fourSeaterPojo);
        return "redirect:/192.168.1.1.1";
    }

    @GetMapping("/add-blogs")
    public String getAddBlogsPage(Model model) {
        model.addAttribute("blogs", new BlogsPojo());
        return "add-blog";
    }
    @PostMapping("/save/blogs")
    public String saveBlogs(@Valid BlogsPojo blogsPojo) throws IOException {
        blogsService.save(blogsPojo);
        return "redirect:/192.168.1.1.1";
    }
}

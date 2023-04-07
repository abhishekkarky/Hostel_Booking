package com.system.hostel_booking_system.controller;

import com.system.hostel_booking_system.entity.DoubleSeater;
import com.system.hostel_booking_system.entity.FourSeater;
import com.system.hostel_booking_system.entity.SingleSeater;
import com.system.hostel_booking_system.entity.TripleSeater;
import com.system.hostel_booking_system.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/room")
public class RoomController {
    private final SingleSeaterService singleSeaterService;
    private final DoubleSeaterService doubleSeaterService;
    private final TripleSeaterService tripleSeaterService;
    private final FourSeaterService fourSeaterService;

    @GetMapping("/single-seater")
    public String getSingleSeater(Model model) {
        List<SingleSeater> singleSeaters = singleSeaterService.fetchAll();
        model.addAttribute("ss", singleSeaters);
        return "single-seater";
    }

    @GetMapping("/double-seater")
    public String getDoubleSeater(Model model) {
        List<DoubleSeater> doubleSeaters = doubleSeaterService.fetchAll();
        model.addAttribute("ds", doubleSeaters);
        return "double-seater";
    }

    @GetMapping("/triple-seater")
    public String getTripleSeater(Model model) {
        List<TripleSeater> tripleSeaters = tripleSeaterService.fetchAll();
        model.addAttribute("ts", tripleSeaters);
        return "triple-seater";
    }

    @GetMapping("/four-seater")
    public String getFourSeater(Model model) {
        List<FourSeater> fourSeaters = fourSeaterService.fetchAll();
        model.addAttribute("fs", fourSeaters);
        return "four-seater";
    }


    @GetMapping("/description")
        public String getRoomDescription() {
        return "room-description";
    }

}

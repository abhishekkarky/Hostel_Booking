package com.system.hostel_booking_system.controller;

import com.system.hostel_booking_system.entity.DoubleSeater;
import com.system.hostel_booking_system.entity.FourSeater;
import com.system.hostel_booking_system.entity.SingleSeater;
import com.system.hostel_booking_system.entity.TripleSeater;
import com.system.hostel_booking_system.pojo.*;
import com.system.hostel_booking_system.service.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/room")
public class RoomController {
    private final SingleSeaterService singleSeaterService;
    private final DoubleSeaterService doubleSeaterService;
    private final TripleSeaterService tripleSeaterService;
    private final FourSeaterService fourSeaterService;
    private final BookingService bookingService;

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

    @GetMapping("/single-seater-room-description/{id}")
        public String getRoomDescriptionSingle(@PathVariable("id") Integer id, Model model) {
        SingleSeater singleSeaters = singleSeaterService.fetchById(id);
        model.addAttribute("descriptionSingle", new SingleSeaterPojo(singleSeaters));
        return "room-description-single";
    }

    @GetMapping("/double-seater-room-description/{id}")
    public String getRoomDescriptionDouble(@PathVariable("id") Integer id, Model model) {
        DoubleSeater doubleSeaters = doubleSeaterService.fetchById(id);
        model.addAttribute("descriptionDouble", new DoubleSeaterPojo(doubleSeaters));
        return "room-description-double";
    }

    @GetMapping("/triple-seater-room-description/{id}")
    public String getRoomDescriptionTriple(@PathVariable("id") Integer id, Model model) {
        TripleSeater tripleSeaters = tripleSeaterService.fetchById(id);
        model.addAttribute("descriptionTriple", new TripleSeaterPojo(tripleSeaters));
        return "room-description-triple";
    }

    @GetMapping("/four-seater-room-description/{id}")
    public String getRoomDescriptionFour(@PathVariable("id") Integer id, Model model) {
        FourSeater fourSeaters = fourSeaterService.fetchById(id);
        model.addAttribute("descriptionFour", new FourSeaterPojo(fourSeaters));
        return "room-description-four";
    }

    @GetMapping("/booking-single-seater/{id}")
    public String getSingleBookingForm(@PathVariable("id") Integer id, Model model) {
        SingleSeater singleSeaters = singleSeaterService.fetchById(id);
        model.addAttribute("bookingSingle", new BookingPojo(singleSeaters));
        return "booking-form-single";
    }

    @GetMapping("/booking-double-seater/{id}")
    public String getDoubleBookingForm(@PathVariable("id") Integer id, Model model) {
        DoubleSeater doubleSeaters = doubleSeaterService.fetchById(id);
        model.addAttribute("bookingDouble", new BookingPojo(doubleSeaters));
        return "booking-form-double";
    }

    @GetMapping("/booking-triple-seater/{id}")
    public String getTripleBookingForm(@PathVariable("id") Integer id, Model model) {
        TripleSeater tripleSeaters = tripleSeaterService.fetchById(id);
        model.addAttribute("bookingTriple", new BookingPojo(tripleSeaters));
        return "booking-form-triple";
    }

    @GetMapping("/booking-four-seater/{id}")
    public String getFourBookingForm(@PathVariable("id") Integer id, Model model) {
        FourSeater fourSeaters = fourSeaterService.fetchById(id);
        model.addAttribute("bookingFour", new BookingPojo(fourSeaters));
        return "booking-form-four";
    }

    @PostMapping("/saveBookingSingle")
    public String saveBookingSingle(@Valid BookingPojo bookingPojo) {
        bookingService.save(bookingPojo);
        return "redirect:/landing";
    }

    @PostMapping("/saveBookingDouble")
    public String saveBookingDouble(@Valid BookingPojo bookingPojo) {
        bookingService.save(bookingPojo);
        return "redirect:/landing";
    }

    @PostMapping("/saveBookingTriple")
    public String saveBookingTriple(@Valid BookingPojo bookingPojo) {
        bookingService.save(bookingPojo);
        return "redirect:/landing";
    }

    @PostMapping("/saveBookingFour")
    public String saveBooking(@Valid BookingPojo bookingPojo) {
        bookingService.save(bookingPojo);
        return "redirect:/landing";
    }

    @GetMapping("/search-single-seater/{searchLocation}")
    public String searchSingleSeater(@PathVariable String searchLocation,Model model) {
        model.addAttribute(searchLocation);

        List<SingleSeater> singleSeaters = singleSeaterService.fetchAll();
        List<SingleSeater> allSingleSeaters = new ArrayList<>();
        for (SingleSeater value:singleSeaters) {
            allSingleSeaters.addAll(getAllSingleSeater(value.getId()));
        }

        for (int i=0; i<allSingleSeaters.size();){
            try {
                if (!allSingleSeaters.get(i).getLocation().substring(0, searchLocation.length()).toLowerCase().equalsIgnoreCase(searchLocation) && !allSingleSeaters.get(i).getLocation().substring(0, searchLocation.length()).equalsIgnoreCase(searchLocation)) {
                    allSingleSeaters.remove(i);
                } else {
                    i++;
                }
            } catch (StringIndexOutOfBoundsException ex){
                allSingleSeaters.remove(i);
            }
        }

        model.addAttribute("singleSeaterList", allSingleSeaters);
        return "search-single-seater";
    }

    public List<SingleSeater> getAllSingleSeater(@PathVariable("id") Integer categoryId){
        return singleSeaterService.fetchAllByLocation(categoryId);
    }

    @GetMapping("/sort-single-seater")
    public String sortSingleSeater(Model model) {
        List<SingleSeater> singleSeaters = getAllSortedSingleSeater();
        model.addAttribute("sortedSingle", singleSeaters);
        return "sort-single-seater";
    }

    public List<SingleSeater> getAllSortedSingleSeater() {
        return  singleSeaterService.fetchAllBySortedPrice();
    }

    @GetMapping("/search-double-seater/{searchLocation}")
    public String searchDoubleSeater(@PathVariable String searchLocation,Model model) {
        model.addAttribute(searchLocation);

        List<DoubleSeater> doubleSeaters = doubleSeaterService.fetchAll();
        List<DoubleSeater> allDoubleSeaters = new ArrayList<>();
        for (DoubleSeater value:doubleSeaters) {
            allDoubleSeaters.addAll(getAllDoubleSeater(value.getId()));
        }

        for (int i=0; i<allDoubleSeaters.size();){
            try {
                if (!allDoubleSeaters.get(i).getLocation().substring(0, searchLocation.length()).toLowerCase().equalsIgnoreCase(searchLocation) && !allDoubleSeaters.get(i).getLocation().substring(0, searchLocation.length()).equalsIgnoreCase(searchLocation)) {
                    allDoubleSeaters.remove(i);
                } else {
                    i++;
                }
            } catch (StringIndexOutOfBoundsException ex){
                allDoubleSeaters.remove(i);
            }
        }

        model.addAttribute("doubleSeaterList", allDoubleSeaters);
        return "search-double-seater";
    }

    public List<DoubleSeater> getAllDoubleSeater(@PathVariable("id") Integer categoryId){
        return doubleSeaterService.fetchAllByLocation(categoryId);
    }

    @GetMapping("/sort-double-seater")
    public String sortDoubleSeater(Model model) {
        List<DoubleSeater> doubleSeaters = getAllSortedDoubleSeater();
        model.addAttribute("sortedDouble", doubleSeaters);
        return "sort-double-seater";
    }

    public List<DoubleSeater> getAllSortedDoubleSeater() {
        return  doubleSeaterService.fetchAllBySortedPrice();
    }

    @GetMapping("/search-triple-seater/{searchLocation}")
    public String searchTripleSeater(@PathVariable String searchLocation,Model model) {
        model.addAttribute(searchLocation);

        List<TripleSeater> tripleSeaters = tripleSeaterService.fetchAll();
        List<TripleSeater> allTripleSeaters = new ArrayList<>();
        for (TripleSeater value:tripleSeaters) {
            allTripleSeaters.addAll(getAllTripleSeater(value.getId()));
        }

        for (int i=0; i<allTripleSeaters.size();){
            try {
                if (!allTripleSeaters.get(i).getLocation().substring(0, searchLocation.length()).toLowerCase().equalsIgnoreCase(searchLocation) && !allTripleSeaters.get(i).getLocation().substring(0, searchLocation.length()).equalsIgnoreCase(searchLocation)) {
                    allTripleSeaters.remove(i);
                } else {
                    i++;
                }
            } catch (StringIndexOutOfBoundsException ex){
                allTripleSeaters.remove(i);
            }
        }

        model.addAttribute("tripleSeaterList", allTripleSeaters);
        return "search-triple-seater";
    }

    public List<TripleSeater> getAllTripleSeater(@PathVariable("id") Integer categoryId){
        return tripleSeaterService.fetchAllByLocation(categoryId);
    }

    @GetMapping("/sort-triple-seater")
    public String sortTripleSeater(Model model) {
        List<TripleSeater> tripleSeaters = getAllSortedTripleSeater();
        model.addAttribute("sortedTriple", tripleSeaters);
        return "sort-triple-seater";
    }

    public List<TripleSeater> getAllSortedTripleSeater() {
        return  tripleSeaterService.fetchAllBySortedPrice();
    }

    @GetMapping("/search-four-seater/{searchLocation}")
    public String searchFourSeater(@PathVariable String searchLocation,Model model) {
        model.addAttribute(searchLocation);

        List<FourSeater> fourSeaters = fourSeaterService.fetchAll();
        List<FourSeater> getAllFourSeater = new ArrayList<>();
        for (FourSeater value:fourSeaters) {
            getAllFourSeater.addAll(getAllFourSeater(value.getId()));
        }

        for (int i=0; i<getAllFourSeater.size();){
            try {
                if (!getAllFourSeater.get(i).getLocation().substring(0, searchLocation.length()).toLowerCase().equalsIgnoreCase(searchLocation) && !getAllFourSeater.get(i).getLocation().substring(0, searchLocation.length()).equalsIgnoreCase(searchLocation)) {
                    getAllFourSeater.remove(i);
                } else {
                    i++;
                }
            } catch (StringIndexOutOfBoundsException ex){
                getAllFourSeater.remove(i);
            }
        }

        model.addAttribute("fourSeaterList", getAllFourSeater);
        return "search-four-seater";
    }

    public List<FourSeater> getAllFourSeater(@PathVariable("id") Integer categoryId){
        return fourSeaterService.fetchAllByLocation(categoryId);
    }

    @GetMapping("/sort-four-seater")
    public String sortFourSeater(Model model) {
        List<FourSeater> fourSeaters = getAllSortedFourSeater();
        model.addAttribute("sortedFour", fourSeaters);
        return "sort-four-seater";
    }

    public List<FourSeater> getAllSortedFourSeater() {
        return  fourSeaterService.fetchAllBySortedPrice();
    }



}

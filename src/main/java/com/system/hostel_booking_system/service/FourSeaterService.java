package com.system.hostel_booking_system.service;

import com.system.hostel_booking_system.entity.FourSeater;
import com.system.hostel_booking_system.pojo.FourSeaterPojo;

import java.io.IOException;
import java.util.List;

public interface FourSeaterService {
    String save(FourSeaterPojo fourSeaterPojo) throws IOException;

    List<FourSeater> fetchAll();

    FourSeater fetchById(Integer id);

    void deleteById(Integer id);

    List<FourSeater> fetchMostRecent();

    List<FourSeater> fetchAllByLocation(Integer categoryId);

    List<FourSeater> fetchAllBySortedPrice();
}

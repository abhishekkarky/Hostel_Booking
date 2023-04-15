package com.system.hostel_booking_system.service;

import com.system.hostel_booking_system.entity.DoubleSeater;
import com.system.hostel_booking_system.pojo.DoubleSeaterPojo;

import java.io.IOException;
import java.util.List;

public interface DoubleSeaterService {
    String save(DoubleSeaterPojo doubleSeaterPojo) throws IOException;

    List<DoubleSeater> fetchAll();


    DoubleSeater fetchById(Integer id);

    void deleteById(Integer id);

    List<DoubleSeater> fetchMostRecent();

    List<DoubleSeater> fetchAllByLocation(Integer categoryId);

    List<DoubleSeater> fetchAllBySortedPrice();

}

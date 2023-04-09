package com.system.hostel_booking_system.service;

import com.system.hostel_booking_system.entity.SingleSeater;
import com.system.hostel_booking_system.pojo.SingleSeaterPojo;

import java.io.IOException;
import java.util.List;

public interface SingleSeaterService {
    String save(SingleSeaterPojo singleSeaterPojo) throws IOException;

    List<SingleSeater> fetchAll();

    SingleSeater fetchById(Integer id);

    void deleteById(Integer id);
}

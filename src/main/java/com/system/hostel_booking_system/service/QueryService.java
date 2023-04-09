package com.system.hostel_booking_system.service;

import com.system.hostel_booking_system.entity.Queries;
import com.system.hostel_booking_system.pojo.QueriesPojo;

import java.util.List;

public interface QueryService {
    String save(QueriesPojo queriesPojo);

    List<Queries> fetchAll();

    void deleteById(Integer id);
}

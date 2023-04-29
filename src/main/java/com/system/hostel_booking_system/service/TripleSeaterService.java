package com.system.hostel_booking_system.service;

import com.system.hostel_booking_system.entity.TripleSeater;
import com.system.hostel_booking_system.pojo.TripleSeaterPojo;

import java.io.IOException;
import java.util.List;

public interface TripleSeaterService {
    String save(TripleSeaterPojo tripleSeaterPojo) throws IOException;

    List<TripleSeater> fetchAll();

    TripleSeater fetchById(Integer id);

    void deleteById(Integer id);

    List<TripleSeater> fetchMostRecent();

    List<TripleSeater> fetchAllByLocation(Integer categoryId);

    List<TripleSeater> fetchAllBySortedPrice();

    TripleSeater findById(Integer id);

}

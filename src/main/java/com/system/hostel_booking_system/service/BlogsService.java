package com.system.hostel_booking_system.service;

import com.system.hostel_booking_system.entity.Blogs;
import com.system.hostel_booking_system.pojo.BlogsPojo;

import java.io.IOException;
import java.util.List;

public interface BlogsService {
    String save(BlogsPojo blogsPojo) throws IOException;

    List<Blogs> fetchAll();

    Blogs fetchById(Integer id);

    void deleteById(Integer id);

    List<Blogs> fetchMostRecent();
}

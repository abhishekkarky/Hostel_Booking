package com.system.hostel_booking_system.service.impl;

import com.system.hostel_booking_system.entity.Queries;
import com.system.hostel_booking_system.pojo.QueriesPojo;
import com.system.hostel_booking_system.repo.QueriesRepo;
import com.system.hostel_booking_system.service.QueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QueryServiceImpl implements QueryService {
    public final QueriesRepo queriesRepo;

    @Override
    public String save(QueriesPojo queriesPojo) {
        Queries queries=new Queries();
        queries.setName(queriesPojo.getName());
        queries.setEmail(queriesPojo.getEmail());
        queries.setPhone(queriesPojo.getPhone());
        queries.setAddress(queriesPojo.getAddress());
        queries.setSubject(queriesPojo.getSubject());
        queries.setMessage(queriesPojo.getMessage());
        queriesRepo.save(queries);
        return "saved";
    }

    @Override
    public List<Queries> fetchAll() {
        return this.queriesRepo.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        this.queriesRepo.deleteById(id);
    }

    public Long countRows() {
        return queriesRepo.countAllRows();
    }
}

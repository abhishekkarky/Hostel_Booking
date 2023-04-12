package com.system.hostel_booking_system.service.impl;

import com.system.hostel_booking_system.entity.SingleSeater;
import com.system.hostel_booking_system.pojo.SingleSeaterPojo;
import com.system.hostel_booking_system.repo.SingleSeaterRepo;
import com.system.hostel_booking_system.service.SingleSeaterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class SingleSeaterServiceImpl implements SingleSeaterService {
    public final SingleSeaterRepo singleSeaterRepo;
    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/Hostel_Booking";

    @Override
    public String save(SingleSeaterPojo singleSeaterPojo) throws IOException {
        SingleSeater singleSeater;
        if (singleSeaterPojo.getId() != null) {
            singleSeater = singleSeaterRepo.findById(singleSeaterPojo.getId()).orElseThrow(() -> new RuntimeException("Not Found"));
        } else {
            singleSeater = new SingleSeater();
        }

        if(singleSeaterPojo.getId()!=null){
            singleSeater.setId(singleSeaterPojo.getId());
        }
        singleSeater.setName(singleSeaterPojo.getName());
        singleSeater.setLocation(singleSeaterPojo.getLocation());
        singleSeater.setPrice(singleSeaterPojo.getPrice());
        singleSeater.setDescription(singleSeaterPojo.getDescription());
        if(singleSeaterPojo.getPhoto()!=null){
            StringBuilder fileNames = new StringBuilder();
            System.out.println(UPLOAD_DIRECTORY);
            Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, singleSeaterPojo.getPhoto().getOriginalFilename());
            fileNames.append(singleSeaterPojo.getPhoto().getOriginalFilename());
            Files.write(fileNameAndPath, singleSeaterPojo.getPhoto().getBytes());

            singleSeater.setPhoto(singleSeaterPojo.getPhoto().getOriginalFilename());
        }

        singleSeaterRepo.save(singleSeater);
        return "created";
    }

    @Override
    public List<SingleSeater> fetchAll() {
        return findAllInList(singleSeaterRepo.findAll());
    }

    @Override
    public SingleSeater fetchById(Integer id) {
        return singleSeaterRepo.findById(id).orElseThrow(()->new RuntimeException("not found"));
    }

    @Override
    public void deleteById(Integer id) {
        singleSeaterRepo.deleteById(id);
    }

    private List<SingleSeater> findAllInList(List<SingleSeater> list) {
        Stream<SingleSeater> allCart=list.stream().map(singleSeater ->
                SingleSeater.builder()
                        .id(singleSeater.getId())
                        .imageBase64(getImageBase64(singleSeater.getPhoto()))
                        .name(singleSeater.getName())
                        .location(singleSeater.getLocation())
                        .price(singleSeater.getPrice())
                        .description(singleSeater.getDescription())
                        .build()
        );

        list = allCart.toList();
        return list;
    }
    public String getImageBase64(String fileName) {
        String filePath = System.getProperty("user.dir") + "/Hostel_Booking/";
        File file = new File(filePath + fileName);
        byte[] bytes = new byte[0];
        try {
            bytes = Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        String base64 = Base64.getEncoder().encodeToString(bytes);
        return base64;
    }

    public Long countRows() {
        return singleSeaterRepo.countAllRows();
    }

    @Override
    public List<SingleSeater> fetchMostRecent() {
        return listMapping(singleSeaterRepo.findMostRecent().orElseThrow(()->new RuntimeException("Not Found")));
    }

    public List<SingleSeater> listMapping(List<SingleSeater> list) {
        Stream<SingleSeater> recentSingle = list.stream().map(singleSeater ->
                SingleSeater.builder()
                        .id(singleSeater.getId())
                        .name(singleSeater.getName())
                        .location(singleSeater.getLocation())
                        .imageBase64(getImageBase64(singleSeater.getPhoto()))
                        .price(singleSeater.getPrice())
                        .description(singleSeater.getDescription())
                        .build()
        );
        list = recentSingle.toList();
        return list;
    }
}

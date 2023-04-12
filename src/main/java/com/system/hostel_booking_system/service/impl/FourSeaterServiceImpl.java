package com.system.hostel_booking_system.service.impl;

import com.system.hostel_booking_system.entity.FourSeater;
import com.system.hostel_booking_system.pojo.FourSeaterPojo;
import com.system.hostel_booking_system.repo.FourSeaterRepo;
import com.system.hostel_booking_system.service.FourSeaterService;
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
public class FourSeaterServiceImpl implements FourSeaterService {
    public final FourSeaterRepo fourSeaterRepo;
    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/Hostel_Booking";

    @Override
    public String save(FourSeaterPojo fourSeaterPojo) throws IOException {
        FourSeater fourSeater;
        if (fourSeaterPojo.getId() != null) {
            fourSeater = fourSeaterRepo.findById(fourSeaterPojo.getId()).orElseThrow(() -> new RuntimeException("Not Found"));
        } else {
            fourSeater = new FourSeater();
        }

        if(fourSeaterPojo.getId()!=null){
            fourSeater.setId(fourSeaterPojo.getId());
        }
        fourSeater.setName(fourSeaterPojo.getName());
        fourSeater.setLocation(fourSeaterPojo.getLocation());
        fourSeater.setPrice(fourSeaterPojo.getPrice());
        fourSeater.setDescription(fourSeaterPojo.getDescription());
        if(fourSeaterPojo.getPhoto()!=null){
            StringBuilder fileNames = new StringBuilder();
            System.out.println(UPLOAD_DIRECTORY);
            Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, fourSeaterPojo.getPhoto().getOriginalFilename());
            fileNames.append(fourSeaterPojo.getPhoto().getOriginalFilename());
            Files.write(fileNameAndPath, fourSeaterPojo.getPhoto().getBytes());

            fourSeater.setPhoto(fourSeaterPojo.getPhoto().getOriginalFilename());
        }

        fourSeaterRepo.save(fourSeater);
        return "created";
    }

    @Override
    public List<FourSeater> fetchAll() {
        return findAllInList(fourSeaterRepo.findAll());
    }

    private List<FourSeater> findAllInList(List<FourSeater> list) {
        Stream<FourSeater> allCart=list.stream().map(fourSeater ->
                FourSeater.builder()
                        .id(fourSeater.getId())
                        .imageBase64(getImageBase64(fourSeater.getPhoto()))
                        .name(fourSeater.getName())
                        .location(fourSeater.getLocation())
                        .price(fourSeater.getPrice())
                        .description(fourSeater.getDescription())
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

    @Override
    public FourSeater fetchById(Integer id) {
        return fourSeaterRepo.findById(id).orElseThrow(()->new RuntimeException("not found"));
    }

    @Override
    public void deleteById(Integer id) {
        fourSeaterRepo.deleteById(id);
    }
    public Long countRows() {
        return fourSeaterRepo.countAllRows();
    }

    @Override
    public List<FourSeater> fetchMostRecent() {
        return listMapping(fourSeaterRepo.findMostRecent().orElseThrow(()->new RuntimeException("Not Found")));
    }

    public List<FourSeater> listMapping(List<FourSeater> list) {
        Stream<FourSeater> recentFour = list.stream().map(fourSeater ->
                FourSeater.builder()
                        .id(fourSeater.getId())
                        .name(fourSeater.getName())
                        .location(fourSeater.getLocation())
                        .imageBase64(getImageBase64(fourSeater.getPhoto()))
                        .price(fourSeater.getPrice())
                        .description(fourSeater.getDescription())
                        .build()
        );
        list = recentFour.toList();
        return list;
    }
}

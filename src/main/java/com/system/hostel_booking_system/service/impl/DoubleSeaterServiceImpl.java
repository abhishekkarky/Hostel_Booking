package com.system.hostel_booking_system.service.impl;

import com.system.hostel_booking_system.entity.DoubleSeater;
import com.system.hostel_booking_system.pojo.DoubleSeaterPojo;
import com.system.hostel_booking_system.repo.DoubleSeaterRepo;
import com.system.hostel_booking_system.service.DoubleSeaterService;
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
public class DoubleSeaterServiceImpl implements DoubleSeaterService {
    public final DoubleSeaterRepo doubleSeaterRepo;
    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/Hostel_Booking";

    @Override
    public String save(DoubleSeaterPojo doubleSeaterPojo) throws IOException {
        DoubleSeater doubleSeater;
        if (doubleSeaterPojo.getId() != null) {
            doubleSeater = doubleSeaterRepo.findById(doubleSeaterPojo.getId()).orElseThrow(() -> new RuntimeException("Not Found"));
        } else {
            doubleSeater = new DoubleSeater();
        }

        if(doubleSeaterPojo.getId()!=null){
            doubleSeater.setId(doubleSeaterPojo.getId());
        }
        doubleSeater.setName(doubleSeaterPojo.getName());
        doubleSeater.setLocation(doubleSeaterPojo.getLocation());
        doubleSeater.setPrice(doubleSeaterPojo.getPrice());
        doubleSeater.setDescription(doubleSeaterPojo.getDescription());
        if(doubleSeaterPojo.getPhoto()!=null){
            StringBuilder fileNames = new StringBuilder();
            System.out.println(UPLOAD_DIRECTORY);
            Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, doubleSeaterPojo.getPhoto().getOriginalFilename());
            fileNames.append(doubleSeaterPojo.getPhoto().getOriginalFilename());
            Files.write(fileNameAndPath, doubleSeaterPojo.getPhoto().getBytes());

            doubleSeater.setPhoto(doubleSeaterPojo.getPhoto().getOriginalFilename());
        }

        doubleSeaterRepo.save(doubleSeater);
        return "created";
    }

    @Override
    public List<DoubleSeater> fetchAll() {
        return findAllInList(doubleSeaterRepo.findAll());
    }

    private List<DoubleSeater> findAllInList(List<DoubleSeater> list) {
        Stream<DoubleSeater> allCart=list.stream().map(doubleSeater ->
                DoubleSeater.builder()
                        .id(doubleSeater.getId())
                        .imageBase64(getImageBase64(doubleSeater.getPhoto()))
                        .name(doubleSeater.getName())
                        .location(doubleSeater.getLocation())
                        .price(doubleSeater.getPrice())
                        .description(doubleSeater.getDescription())
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
    public DoubleSeater fetchById(Integer id) {
        return doubleSeaterRepo.findById(id).orElseThrow(()->new RuntimeException("not found"));
    }

    @Override
    public void deleteById(Integer id) {
        doubleSeaterRepo.deleteById(id);
    }

    public Long countRows() {
        return doubleSeaterRepo.countAllRows();
    }
}

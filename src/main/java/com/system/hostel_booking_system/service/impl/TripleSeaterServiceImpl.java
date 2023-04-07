package com.system.hostel_booking_system.service.impl;

import com.system.hostel_booking_system.entity.TripleSeater;
import com.system.hostel_booking_system.pojo.TripleSeaterPojo;
import com.system.hostel_booking_system.repo.TripleSeaterRepo;
import com.system.hostel_booking_system.service.TripleSeaterService;
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
public class TripleSeaterServiceImpl implements TripleSeaterService {
    public final TripleSeaterRepo tripleSeaterRepo;
    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/Hostel_Booking";

    @Override
    public String save(TripleSeaterPojo tripleSeaterPojo) throws IOException {
        TripleSeater tripleSeater;
        if (tripleSeaterPojo.getId() != null) {
            tripleSeater = tripleSeaterRepo.findById(tripleSeaterPojo.getId()).orElseThrow(() -> new RuntimeException("Not Found"));
        } else {
            tripleSeater = new TripleSeater();
        }

        if(tripleSeaterPojo.getId()!=null){
            tripleSeater.setId(tripleSeaterPojo.getId());
        }
        tripleSeater.setName(tripleSeaterPojo.getName());
        tripleSeater.setLocation(tripleSeaterPojo.getLocation());
        tripleSeater.setPrice(tripleSeaterPojo.getPrice());
        tripleSeater.setDescription(tripleSeaterPojo.getDescription());
        if(tripleSeaterPojo.getPhoto()!=null){
            StringBuilder fileNames = new StringBuilder();
            System.out.println(UPLOAD_DIRECTORY);
            Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, tripleSeaterPojo.getPhoto().getOriginalFilename());
            fileNames.append(tripleSeaterPojo.getPhoto().getOriginalFilename());
            Files.write(fileNameAndPath, tripleSeaterPojo.getPhoto().getBytes());

            tripleSeater.setPhoto(tripleSeaterPojo.getPhoto().getOriginalFilename());
        }

        tripleSeaterRepo.save(tripleSeater);
        return "created";
    }

    @Override
    public List<TripleSeater> fetchAll() {
        return findAllInList(tripleSeaterRepo.findAll());
    }

    private List<TripleSeater> findAllInList(List<TripleSeater> list) {
        Stream<TripleSeater> allCart=list.stream().map(tripleSeater ->
                TripleSeater.builder()
                        .id(tripleSeater.getId())
                        .imageBase64(getImageBase64(tripleSeater.getPhoto()))
                        .name(tripleSeater.getName())
                        .location(tripleSeater.getLocation())
                        .price(tripleSeater.getPrice())
                        .description(tripleSeater.getDescription())
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
        return tripleSeaterRepo.countAllRows();
    }
}

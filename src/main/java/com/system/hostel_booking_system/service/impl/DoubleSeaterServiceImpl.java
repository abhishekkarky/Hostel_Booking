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
import java.util.Objects;
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

        if(!Objects.equals(doubleSeaterPojo.getPhoto().getOriginalFilename(), "")){
            StringBuilder fileNames = new StringBuilder();
            System.out.println(UPLOAD_DIRECTORY);
            Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, doubleSeaterPojo.getPhoto().getOriginalFilename());
            fileNames.append(doubleSeaterPojo.getPhoto().getOriginalFilename());
            Files.write(fileNameAndPath, doubleSeaterPojo.getPhoto().getBytes());
            doubleSeater.setPhoto(doubleSeaterPojo.getPhoto().getOriginalFilename());
        }

        if(doubleSeaterPojo.getPhoto2()!=null){
            StringBuilder fileNames = new StringBuilder();
            System.out.println(UPLOAD_DIRECTORY);
            Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, doubleSeaterPojo.getPhoto2().getOriginalFilename());
            fileNames.append(doubleSeaterPojo.getPhoto2().getOriginalFilename());
            Files.write(fileNameAndPath, doubleSeaterPojo.getPhoto2().getBytes());
            doubleSeater.setPhoto2(doubleSeaterPojo.getPhoto2().getOriginalFilename());
        }

        if(doubleSeaterPojo.getPhoto3()!=null){
            StringBuilder fileNames = new StringBuilder();
            System.out.println(UPLOAD_DIRECTORY);
            Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, doubleSeaterPojo.getPhoto3().getOriginalFilename());
            fileNames.append(doubleSeaterPojo.getPhoto3().getOriginalFilename());
            Files.write(fileNameAndPath, doubleSeaterPojo.getPhoto3().getBytes());
            doubleSeater.setPhoto3(doubleSeaterPojo.getPhoto3().getOriginalFilename());
        }

        if(doubleSeaterPojo.getPhoto4()!=null){
            StringBuilder fileNames = new StringBuilder();
            System.out.println(UPLOAD_DIRECTORY);
            Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, doubleSeaterPojo.getPhoto4().getOriginalFilename());
            fileNames.append(doubleSeaterPojo.getPhoto4().getOriginalFilename());
            Files.write(fileNameAndPath, doubleSeaterPojo.getPhoto4().getBytes());
            doubleSeater.setPhoto4(doubleSeaterPojo.getPhoto4().getOriginalFilename());
        }

        if(doubleSeaterPojo.getPhoto5()!=null){
            StringBuilder fileNames = new StringBuilder();
            System.out.println(UPLOAD_DIRECTORY);
            Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, doubleSeaterPojo.getPhoto5().getOriginalFilename());
            fileNames.append(doubleSeaterPojo.getPhoto5().getOriginalFilename());
            Files.write(fileNameAndPath, doubleSeaterPojo.getPhoto5().getBytes());
            doubleSeater.setPhoto5(doubleSeaterPojo.getPhoto5().getOriginalFilename());
        }

        if(doubleSeaterPojo.getPhoto6()!=null){
            StringBuilder fileNames = new StringBuilder();
            System.out.println(UPLOAD_DIRECTORY);
            Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, doubleSeaterPojo.getPhoto6().getOriginalFilename());
            fileNames.append(doubleSeaterPojo.getPhoto6().getOriginalFilename());
            Files.write(fileNameAndPath, doubleSeaterPojo.getPhoto6().getBytes());
            doubleSeater.setPhoto6(doubleSeaterPojo.getPhoto6().getOriginalFilename());
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

    @Override
    public List<DoubleSeater> fetchMostRecent() {
        return listMapping(doubleSeaterRepo.findMostRecent().orElseThrow(()->new RuntimeException("Not Found")));
    }

    @Override
    public List<DoubleSeater> fetchAllByLocation(Integer categoryId) {
        return listMapping(doubleSeaterRepo.findAllByLocation(categoryId).orElseThrow(()->new RuntimeException("Not Found")));
    }

    @Override
    public List<DoubleSeater> fetchAllBySortedPrice() {
        return listMapping(doubleSeaterRepo.findAllBySortedPrice().orElseThrow(()->new RuntimeException("Not Found")));
    }

    public List<DoubleSeater> listMapping(List<DoubleSeater> list) {
        Stream<DoubleSeater> recentDouble = list.stream().map(doubleSeater ->
                DoubleSeater.builder()
                        .id(doubleSeater.getId())
                        .name(doubleSeater.getName())
                        .location(doubleSeater.getLocation())
                        .imageBase64(getImageBase64(doubleSeater.getPhoto()))
                        .price(doubleSeater.getPrice())
                        .description(doubleSeater.getDescription())
                        .build()
        );
        list = recentDouble.toList();
        return list;
    }

    @Override
    public DoubleSeater findById(Integer id) {
        DoubleSeater doubleSeater=doubleSeaterRepo.findById(id).orElseThrow(()-> new RuntimeException("not found"));
        doubleSeater=DoubleSeater.builder()
                .id(doubleSeater.getId())
                .name(doubleSeater.getName())
                .location(doubleSeater.getLocation())
                .imageBase64(getImageBase64(doubleSeater.getPhoto()))
                .image2Base64(getImageBase64(doubleSeater.getPhoto2()))
                .image3Base64(getImageBase64(doubleSeater.getPhoto3()))
                .image4Base64(getImageBase64(doubleSeater.getPhoto4()))
                .image5Base64(getImageBase64(doubleSeater.getPhoto5()))
                .image6Base64(getImageBase64(doubleSeater.getPhoto6()))
                .description(doubleSeater.getDescription())
                .build();
        return doubleSeater;
    }
}

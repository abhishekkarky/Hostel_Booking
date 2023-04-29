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
import java.util.Objects;
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

        if(!Objects.equals(singleSeaterPojo.getPhoto().getOriginalFilename(), "")){
            StringBuilder fileNames = new StringBuilder();
            System.out.println(UPLOAD_DIRECTORY);
            Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, singleSeaterPojo.getPhoto().getOriginalFilename());
            fileNames.append(singleSeaterPojo.getPhoto().getOriginalFilename());
            Files.write(fileNameAndPath, singleSeaterPojo.getPhoto().getBytes());
            singleSeater.setPhoto(singleSeaterPojo.getPhoto().getOriginalFilename());
        }

        if(singleSeaterPojo.getPhoto2()!=null){
            StringBuilder fileNames = new StringBuilder();
            System.out.println(UPLOAD_DIRECTORY);
            Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, singleSeaterPojo.getPhoto2().getOriginalFilename());
            fileNames.append(singleSeaterPojo.getPhoto2().getOriginalFilename());
            Files.write(fileNameAndPath, singleSeaterPojo.getPhoto2().getBytes());
            singleSeater.setPhoto2(singleSeaterPojo.getPhoto2().getOriginalFilename());
        }

        if(singleSeaterPojo.getPhoto3()!=null){
            StringBuilder fileNames = new StringBuilder();
            System.out.println(UPLOAD_DIRECTORY);
            Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, singleSeaterPojo.getPhoto3().getOriginalFilename());
            fileNames.append(singleSeaterPojo.getPhoto3().getOriginalFilename());
            Files.write(fileNameAndPath, singleSeaterPojo.getPhoto3().getBytes());
            singleSeater.setPhoto3(singleSeaterPojo.getPhoto3().getOriginalFilename());
        }

        if(singleSeaterPojo.getPhoto4()!=null){
            StringBuilder fileNames = new StringBuilder();
            System.out.println(UPLOAD_DIRECTORY);
            Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, singleSeaterPojo.getPhoto4().getOriginalFilename());
            fileNames.append(singleSeaterPojo.getPhoto4().getOriginalFilename());
            Files.write(fileNameAndPath, singleSeaterPojo.getPhoto4().getBytes());
            singleSeater.setPhoto4(singleSeaterPojo.getPhoto4().getOriginalFilename());
        }

        if(singleSeaterPojo.getPhoto5()!=null){
            StringBuilder fileNames = new StringBuilder();
            System.out.println(UPLOAD_DIRECTORY);
            Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, singleSeaterPojo.getPhoto5().getOriginalFilename());
            fileNames.append(singleSeaterPojo.getPhoto5().getOriginalFilename());
            Files.write(fileNameAndPath, singleSeaterPojo.getPhoto5().getBytes());
            singleSeater.setPhoto5(singleSeaterPojo.getPhoto5().getOriginalFilename());
        }

        if(singleSeaterPojo.getPhoto6()!=null){
            StringBuilder fileNames = new StringBuilder();
            System.out.println(UPLOAD_DIRECTORY);
            Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, singleSeaterPojo.getPhoto6().getOriginalFilename());
            fileNames.append(singleSeaterPojo.getPhoto6().getOriginalFilename());
            Files.write(fileNameAndPath, singleSeaterPojo.getPhoto6().getBytes());
            singleSeater.setPhoto6(singleSeaterPojo.getPhoto6().getOriginalFilename());
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

    @Override
    public List<SingleSeater> fetchAllByLocation(Integer categoryId) {
        return listMapping(singleSeaterRepo.findAllByLocation(categoryId).orElseThrow(()->new RuntimeException("Not Found")));
    }

    @Override
    public List<SingleSeater> fetchAllBySortedPrice() {
        return listMapping(singleSeaterRepo.findAllBySortedPrice().orElseThrow(()->new RuntimeException("Not Found")));
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

    @Override
    public SingleSeater findById(Integer id) {
        SingleSeater singleSeater=singleSeaterRepo.findById(id).orElseThrow(()-> new RuntimeException("not found"));
        singleSeater=SingleSeater.builder()
                .id(singleSeater.getId())
                .name(singleSeater.getName())
                .location(singleSeater.getLocation())
                .imageBase64(getImageBase64(singleSeater.getPhoto()))
                .image2Base64(getImageBase64(singleSeater.getPhoto2()))
                .image3Base64(getImageBase64(singleSeater.getPhoto3()))
                .image4Base64(getImageBase64(singleSeater.getPhoto4()))
                .image5Base64(getImageBase64(singleSeater.getPhoto5()))
                .image6Base64(getImageBase64(singleSeater.getPhoto6()))
                .description(singleSeater.getDescription())
                .build();
        return singleSeater;
    }
}

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
import java.util.Objects;
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

        if(!Objects.equals(fourSeaterPojo.getPhoto().getOriginalFilename(), "")){
            StringBuilder fileNames = new StringBuilder();
            System.out.println(UPLOAD_DIRECTORY);
            Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, fourSeaterPojo.getPhoto().getOriginalFilename());
            fileNames.append(fourSeaterPojo.getPhoto().getOriginalFilename());
            Files.write(fileNameAndPath, fourSeaterPojo.getPhoto().getBytes());
            fourSeater.setPhoto(fourSeaterPojo.getPhoto().getOriginalFilename());
        }

        if(fourSeaterPojo.getPhoto2()!=null){
            StringBuilder fileNames = new StringBuilder();
            System.out.println(UPLOAD_DIRECTORY);
            Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, fourSeaterPojo.getPhoto2().getOriginalFilename());
            fileNames.append(fourSeaterPojo.getPhoto2().getOriginalFilename());
            Files.write(fileNameAndPath, fourSeaterPojo.getPhoto2().getBytes());
            fourSeater.setPhoto2(fourSeaterPojo.getPhoto2().getOriginalFilename());
        }

        if(fourSeaterPojo.getPhoto3()!=null){
            StringBuilder fileNames = new StringBuilder();
            System.out.println(UPLOAD_DIRECTORY);
            Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, fourSeaterPojo.getPhoto3().getOriginalFilename());
            fileNames.append(fourSeaterPojo.getPhoto3().getOriginalFilename());
            Files.write(fileNameAndPath, fourSeaterPojo.getPhoto3().getBytes());
            fourSeater.setPhoto3(fourSeaterPojo.getPhoto3().getOriginalFilename());
        }

        if(fourSeaterPojo.getPhoto4()!=null){
            StringBuilder fileNames = new StringBuilder();
            System.out.println(UPLOAD_DIRECTORY);
            Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, fourSeaterPojo.getPhoto4().getOriginalFilename());
            fileNames.append(fourSeaterPojo.getPhoto4().getOriginalFilename());
            Files.write(fileNameAndPath, fourSeaterPojo.getPhoto4().getBytes());
            fourSeater.setPhoto4(fourSeaterPojo.getPhoto4().getOriginalFilename());
        }

        if(fourSeaterPojo.getPhoto5()!=null){
            StringBuilder fileNames = new StringBuilder();
            System.out.println(UPLOAD_DIRECTORY);
            Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, fourSeaterPojo.getPhoto5().getOriginalFilename());
            fileNames.append(fourSeaterPojo.getPhoto5().getOriginalFilename());
            Files.write(fileNameAndPath, fourSeaterPojo.getPhoto5().getBytes());
            fourSeater.setPhoto5(fourSeaterPojo.getPhoto5().getOriginalFilename());
        }

        if(fourSeaterPojo.getPhoto6()!=null){
            StringBuilder fileNames = new StringBuilder();
            System.out.println(UPLOAD_DIRECTORY);
            Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, fourSeaterPojo.getPhoto6().getOriginalFilename());
            fileNames.append(fourSeaterPojo.getPhoto6().getOriginalFilename());
            Files.write(fileNameAndPath, fourSeaterPojo.getPhoto6().getBytes());
            fourSeater.setPhoto6(fourSeaterPojo.getPhoto6().getOriginalFilename());
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

    @Override
    public List<FourSeater> fetchAllByLocation(Integer categoryId) {
        return listMapping(fourSeaterRepo.findAllByLocation(categoryId).orElseThrow(()->new RuntimeException("Not Found")));
    }

    @Override
    public List<FourSeater> fetchAllBySortedPrice() {
        return listMapping(fourSeaterRepo.findAllBySortedPrice().orElseThrow(()->new RuntimeException("Not Found")));
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

    @Override
    public FourSeater findById(Integer id) {
        FourSeater fourSeater=fourSeaterRepo.findById(id).orElseThrow(()-> new RuntimeException("not found"));
        fourSeater=FourSeater.builder()
                .id(fourSeater.getId())
                .name(fourSeater.getName())
                .location(fourSeater.getLocation())
                .imageBase64(getImageBase64(fourSeater.getPhoto()))
                .image2Base64(getImageBase64(fourSeater.getPhoto2()))
                .image3Base64(getImageBase64(fourSeater.getPhoto3()))
                .image4Base64(getImageBase64(fourSeater.getPhoto4()))
                .image5Base64(getImageBase64(fourSeater.getPhoto5()))
                .image6Base64(getImageBase64(fourSeater.getPhoto6()))
                .description(fourSeater.getDescription())
                .build();
        return fourSeater;
    }
}

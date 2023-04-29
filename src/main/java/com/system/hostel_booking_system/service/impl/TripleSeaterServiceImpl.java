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
import java.util.Objects;
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

        if(!Objects.equals(tripleSeaterPojo.getPhoto().getOriginalFilename(), "")){
            StringBuilder fileNames = new StringBuilder();
            System.out.println(UPLOAD_DIRECTORY);
            Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, tripleSeaterPojo.getPhoto().getOriginalFilename());
            fileNames.append(tripleSeaterPojo.getPhoto().getOriginalFilename());
            Files.write(fileNameAndPath, tripleSeaterPojo.getPhoto().getBytes());
            tripleSeater.setPhoto(tripleSeaterPojo.getPhoto().getOriginalFilename());
        }
        if(tripleSeaterPojo.getPhoto2()!=null){
            StringBuilder fileNames = new StringBuilder();
            System.out.println(UPLOAD_DIRECTORY);
            Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, tripleSeaterPojo.getPhoto2().getOriginalFilename());
            fileNames.append(tripleSeaterPojo.getPhoto2().getOriginalFilename());
            Files.write(fileNameAndPath, tripleSeaterPojo.getPhoto2().getBytes());
            tripleSeater.setPhoto2(tripleSeaterPojo.getPhoto2().getOriginalFilename());
        }

        if(tripleSeaterPojo.getPhoto3()!=null){
            StringBuilder fileNames = new StringBuilder();
            System.out.println(UPLOAD_DIRECTORY);
            Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, tripleSeaterPojo.getPhoto3().getOriginalFilename());
            fileNames.append(tripleSeaterPojo.getPhoto3().getOriginalFilename());
            Files.write(fileNameAndPath, tripleSeaterPojo.getPhoto3().getBytes());
            tripleSeater.setPhoto3(tripleSeaterPojo.getPhoto3().getOriginalFilename());
        }

        if(tripleSeaterPojo.getPhoto4()!=null){
            StringBuilder fileNames = new StringBuilder();
            System.out.println(UPLOAD_DIRECTORY);
            Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, tripleSeaterPojo.getPhoto4().getOriginalFilename());
            fileNames.append(tripleSeaterPojo.getPhoto4().getOriginalFilename());
            Files.write(fileNameAndPath, tripleSeaterPojo.getPhoto4().getBytes());
            tripleSeater.setPhoto4(tripleSeaterPojo.getPhoto4().getOriginalFilename());
        }
        if(tripleSeaterPojo.getPhoto5()!=null){
            StringBuilder fileNames = new StringBuilder();
            System.out.println(UPLOAD_DIRECTORY);
            Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, tripleSeaterPojo.getPhoto5().getOriginalFilename());
            fileNames.append(tripleSeaterPojo.getPhoto5().getOriginalFilename());
            Files.write(fileNameAndPath, tripleSeaterPojo.getPhoto5().getBytes());
            tripleSeater.setPhoto5(tripleSeaterPojo.getPhoto5().getOriginalFilename());
        }
        if(tripleSeaterPojo.getPhoto6()!=null){
            StringBuilder fileNames = new StringBuilder();
            System.out.println(UPLOAD_DIRECTORY);
            Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, tripleSeaterPojo.getPhoto6().getOriginalFilename());
            fileNames.append(tripleSeaterPojo.getPhoto6().getOriginalFilename());
            Files.write(fileNameAndPath, tripleSeaterPojo.getPhoto6().getBytes());
            tripleSeater.setPhoto6(tripleSeaterPojo.getPhoto6().getOriginalFilename());
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

    @Override
    public TripleSeater fetchById(Integer id) {
        return tripleSeaterRepo.findById(id).orElseThrow(()->new RuntimeException("not found"));
    }

    @Override
    public void deleteById(Integer id) {
        tripleSeaterRepo.deleteById(id);
    }

    public Long countRows() {
        return tripleSeaterRepo.countAllRows();
    }

    @Override
    public List<TripleSeater> fetchMostRecent() {
        return listMapping(tripleSeaterRepo.findMostRecent().orElseThrow(()->new RuntimeException("Not Found")));
    }

    @Override
    public List<TripleSeater> fetchAllByLocation(Integer categoryId) {
        return listMapping(tripleSeaterRepo.findAllByLocation(categoryId).orElseThrow(()->new RuntimeException("Not Found")));
    }

    @Override
    public List<TripleSeater> fetchAllBySortedPrice() {
        return listMapping(tripleSeaterRepo.findAllBySortedPrice().orElseThrow(()->new RuntimeException("Not Found")));
    }

    @Override
    public TripleSeater findById(Integer id) {
        TripleSeater tripleSeater=tripleSeaterRepo.findById(id).orElseThrow(()-> new RuntimeException("not found"));
        tripleSeater=TripleSeater.builder()
                .id(tripleSeater.getId())
                .name(tripleSeater.getName())
                .location(tripleSeater.getLocation())
                .imageBase64(getImageBase64(tripleSeater.getPhoto()))
                .image2Base64(getImageBase64(tripleSeater.getPhoto2()))
                .image3Base64(getImageBase64(tripleSeater.getPhoto3()))
                .image4Base64(getImageBase64(tripleSeater.getPhoto4()))
                .image5Base64(getImageBase64(tripleSeater.getPhoto5()))
                .image6Base64(getImageBase64(tripleSeater.getPhoto6()))
                .description(tripleSeater.getDescription())
                .build();
        return tripleSeater;
    }

    public List<TripleSeater> listMapping(List<TripleSeater> list) {
        Stream<TripleSeater> recentTriple = list.stream().map(tripleSeater ->
                TripleSeater.builder()
                        .id(tripleSeater.getId())
                        .name(tripleSeater.getName())
                        .location(tripleSeater.getLocation())
                        .imageBase64(getImageBase64(tripleSeater.getPhoto()))
                        .price(tripleSeater.getPrice())
                        .description(tripleSeater.getDescription())
                        .build()
        );
        list = recentTriple.toList();
        return list;
    }


}

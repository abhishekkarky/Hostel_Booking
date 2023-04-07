package com.system.hostel_booking_system.service.impl;

import com.system.hostel_booking_system.entity.Blogs;
import com.system.hostel_booking_system.pojo.BlogsPojo;
import com.system.hostel_booking_system.repo.BlogsRepo;
import com.system.hostel_booking_system.service.BlogsService;
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
public class BlogsServiceImpl implements BlogsService {
    public final BlogsRepo blogsRepo;
    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/Hostel_Booking";

    @Override
    public String save(BlogsPojo blogsPojo) throws IOException {
        Blogs blogs;
        if (blogsPojo.getId() != null) {
            blogs = blogsRepo.findById(blogsPojo.getId()).orElseThrow(() -> new RuntimeException("Not Found"));
        } else {
            blogs = new Blogs();
        }

        if(blogsPojo.getId()!=null){
            blogs.setId(blogsPojo.getId());
        }
        blogs.setTopic(blogsPojo.getTopic());
        blogs.setDescription(blogsPojo.getDescription());
        if(blogsPojo.getPhoto()!=null){
            StringBuilder fileNames = new StringBuilder();
            System.out.println(UPLOAD_DIRECTORY);
            Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, blogsPojo.getPhoto().getOriginalFilename());
            fileNames.append(blogsPojo.getPhoto().getOriginalFilename());
            Files.write(fileNameAndPath, blogsPojo.getPhoto().getBytes());

            blogs.setPhoto(blogsPojo.getPhoto().getOriginalFilename());
        }

        blogsRepo.save(blogs);
        return "created";
    }

    @Override
    public List<Blogs> fetchAll() {
        return findAllInList(blogsRepo.findAll());
    }

    private List<Blogs> findAllInList(List<Blogs> list) {
        Stream<Blogs> allCart=list.stream().map(blogs ->
                Blogs.builder()
                        .id(blogs.getId())
                        .imageBase64(getImageBase64(blogs.getPhoto()))
                        .topic(blogs.getTopic())
                        .description(blogs.getDescription())
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
        return blogsRepo.countAllRows();
    }
}

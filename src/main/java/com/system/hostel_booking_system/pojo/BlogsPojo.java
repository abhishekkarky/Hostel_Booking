package com.system.hostel_booking_system.pojo;

import com.system.hostel_booking_system.entity.Blogs;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BlogsPojo {

    private Integer id;

    private MultipartFile photo;

    @NotEmpty(message = "Topic can't be empty")
    private String topic;

    @NotEmpty(message = "Description can't be empty")
    private String description;

    public BlogsPojo(Blogs blogs) {
        this.id = blogs.getId();
        this.topic = blogs.getTopic();
        this.description = blogs.getDescription();
    }
}
package com.system.hostel_booking_system.pojo;

import com.system.hostel_booking_system.entity.SingleSeater;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SingleSeaterPojo {

    private Integer id;

    private MultipartFile photo;
    private MultipartFile photo2;
    private MultipartFile photo3;
    private MultipartFile photo4;
    private MultipartFile photo5;
    private MultipartFile photo6;

    @NotEmpty(message = "Hostel's name can't be empty")
    private String name;

    @NotEmpty(message = "Hostel's location can't be empty")
    private String location;

    @NotEmpty(message = "Hostel's Price can't be empty")
    private String price;

    @NotEmpty(message = "Hostel's Description can't be empty")
    private String description;

    public SingleSeaterPojo(SingleSeater singleSeater) {
        this.id = singleSeater.getId();
        this.name = singleSeater.getName();
        this.location = singleSeater.getLocation();
        this.price = singleSeater.getPrice();
        this.description = singleSeater.getDescription();
    }
}

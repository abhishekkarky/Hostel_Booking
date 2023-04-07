package com.system.hostel_booking_system.pojo;

import com.system.hostel_booking_system.entity.DoubleSeater;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DoubleSeaterPojo {

    private Integer id;

    private MultipartFile photo;

    @NotEmpty(message = "Hostel's name can't be empty")
    private String name;

    @NotEmpty(message = "Hostel's location can't be empty")
    private String location;

    @NotEmpty(message = "Hostel's Price can't be empty")
    private String price;

    @NotEmpty(message = "Hostel's Description can't be empty")
    private String description;

    public DoubleSeaterPojo(DoubleSeater doubleSeater) {
        this.id = doubleSeater.getId();
        this.name = doubleSeater.getName();
        this.location = doubleSeater.getLocation();
        this.price = doubleSeater.getPrice();
        this.description = doubleSeater.getDescription();
    }
}

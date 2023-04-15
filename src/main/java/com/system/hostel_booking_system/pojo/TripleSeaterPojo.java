package com.system.hostel_booking_system.pojo;

import com.system.hostel_booking_system.entity.TripleSeater;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TripleSeaterPojo {

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

    public TripleSeaterPojo(TripleSeater tripleSeater) {
        this.id = tripleSeater.getId();
        this.name = tripleSeater.getName();
        this.location = tripleSeater.getLocation();
        this.price = tripleSeater.getPrice();
        this.description = tripleSeater.getDescription();
    }
}

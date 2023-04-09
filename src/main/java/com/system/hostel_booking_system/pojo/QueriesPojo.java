package com.system.hostel_booking_system.pojo;

import com.system.hostel_booking_system.entity.Queries;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QueriesPojo {
    private Integer id;
    @NotEmpty(message = "Name can't be empty")
    private String name;
    @NotEmpty(message = "E-mail can't be empty")
    private String email;
    @NotEmpty(message = "Phone can't be empty")
    private String phone;
    @NotEmpty(message = "Address can't be empty")
    private String address;
    @NotEmpty(message = "Subject can't be empty")
    private String subject;
    @NotEmpty(message = "Message can't be empty")
    private String message;

    public QueriesPojo(Queries queries) {
        this.id = queries.getId();
        this.name = queries.getName();
        this.email = queries.getEmail();
        this.phone = queries.getPhone();
        this.address = queries.getAddress();
        this.subject = queries.getSubject();
        this.message = queries.getMessage();
    }
}

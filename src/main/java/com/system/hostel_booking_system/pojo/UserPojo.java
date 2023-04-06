package com.system.hostel_booking_system.pojo;

import com.system.hostel_booking_system.entity.User;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserPojo {

    private Integer id;

    @NotEmpty(message = "E-mail can't be empty")
    private String email;

    @NotEmpty(message = "Password can't be empty")
    private String password;

    public UserPojo(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.password = user.getPassword();
    }
}

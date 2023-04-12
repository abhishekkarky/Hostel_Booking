package com.system.hostel_booking_system.pojo;

import com.system.hostel_booking_system.entity.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookingPojo {

    private Integer id;

    @NotEmpty(message = "Hostel name can't be empty")
    private String hostel_name;

    @NotEmpty(message = "Hostel location can't be empty")
    private String hostel_location;

    @NotEmpty(message = "Name can't be empty")
    private String name;

    @NotEmpty(message = "Mobile Number can't be empty")
    private String phone;

    @NotEmpty(message = "Email can't be empty")
    private String email;

    @NotEmpty(message = "Room Type can't be empty")
    private String room_type;

    @NotEmpty(message = "CheckIn can't be Empty")
    private String checkin;

    public BookingPojo(Booking booking) {
        this.id = booking.getId();
        this.hostel_name = booking.getHostel_name();
        this.hostel_location = booking.getHostel_location();
        this.name = booking.getName();
        this.phone = booking.getPhone();
        this.email = booking.getEmail();
        this.room_type = booking.getRoom_type();
        this.checkin = booking.getCheckin();
    }

    public BookingPojo(SingleSeater singleSeaters) {
        this.id = singleSeaters.getId();
        this.hostel_name = singleSeaters.getName();
        this.hostel_location = singleSeaters.getLocation();
    }

    public BookingPojo(DoubleSeater doubleSeater) {
        this.id = doubleSeater.getId();
        this.hostel_name = doubleSeater.getName();
        this.hostel_location = doubleSeater.getLocation();
    }

    public BookingPojo(TripleSeater tripleSeater) {
        this.id = tripleSeater.getId();
        this.hostel_name = tripleSeater.getName();
        this.hostel_location = tripleSeater.getLocation();
    }

    public BookingPojo(FourSeater fourSeater) {
        this.id = fourSeater.getId();
        this.hostel_name = fourSeater.getName();
        this.hostel_location = fourSeater.getLocation();
    }

//    public BookingPojo(SingleSeater singleSeaters, DoubleSeater doubleSeaters, TripleSeater tripleSeaters, FourSeater fourSeaters) {
//        this.id = singleSeaters.getId();
//        this.hostel_name = singleSeaters.getName();
//        this.hostel_location = singleSeaters.getLocation();
//        this.id = doubleSeaters.getId();
//        this.hostel_name = doubleSeaters.getName();
//        this.hostel_location
//    }
}

package com.system.hostel_booking_system.entity;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Booking")
public class Booking {
    @Id
    @SequenceGenerator(name = "Booking_seq_gen", sequenceName = "Booking_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "Booking_seq_gen", strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "HostelName", nullable = false)
    private String hostel_name;

    @Column(name = "HostelLocation", nullable = false)
    private String hostel_location;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "RoomType", nullable = false)
    private String room_type;

    @Column(name = "CheckIn", nullable = false)
    private String checkin;

}

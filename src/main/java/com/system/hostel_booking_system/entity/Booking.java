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

    @Column(name = "Name", nullable = false)
    private String name;

}

package com.system.hostel_booking_system.entity;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Triple_Seater")
public class TripleSeater {
    @Id
    @SequenceGenerator(name = "Triple_Seater_seq_gen", sequenceName = "Triple_Seater_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "Triple_Seater_seq_gen", strategy = GenerationType.SEQUENCE)
    private Integer id;

    private String photo;

    @Column(name = "Hostel_Name", nullable = false)
    private String name;

    @Column(name = "Hostel_Location", nullable = false)
    private String location;

    @Column(name = "Hostel_Price", nullable = false)
    private String price;

    @Column(name = "Hostel_Description", nullable = false)
    private String description;

    @Transient
    private String imageBase64;
}

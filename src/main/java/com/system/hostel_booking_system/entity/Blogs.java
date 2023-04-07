package com.system.hostel_booking_system.entity;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Blogs")
public class Blogs {
    @Id
    @SequenceGenerator(name = "Blogs_seq_gen", sequenceName = "Blogs_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "Blogs_seq_gen", strategy = GenerationType.SEQUENCE)
    private Integer id;

    private String photo;

    @Column(name = "Topic", nullable = false)
    private String topic;

    @Column(name = "Description", nullable = false)
    private String description;

    @Transient
    private String imageBase64;
}

package com.system.hostel_booking_system.entity;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Queries")
public class Queries {
    @Id
    @SequenceGenerator(name = "Queries_seq_gen", sequenceName = "Queries_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "Queries_seq_gen", strategy = GenerationType.SEQUENCE)
    private Integer id;
    @Column(name = "Name", nullable = false)
    private String name;
    @Column(name = "E-mail", nullable = false)
    private String email;
    @Column(name = "Phone", nullable = false)
    private String phone;
    @Column(name = "Address", nullable = false)
    private String address;
    @Column(name = "Subject", nullable = false)
    private String subject;
    @Column(name = "Message", nullable = false)
    private String message;

}

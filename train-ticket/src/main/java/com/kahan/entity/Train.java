package com.kahan.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "train")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Train {

    @Id
    private int trainNo;

    @Column(nullable = false)
    private String trainName;

    @Column(nullable = false)
    private String source;

    @Column(nullable = false)
    private String destination;

    @Column(nullable = false)
    private int numberOfCoaches;  // Each coach has 60 seats

    @Column(nullable = false)
    private int totalSeats;       // Derived = numberOfCoaches * 60

    @Column(nullable = false)
    private double fare;

    @Column(nullable = false)
    private int availableSeats;
}

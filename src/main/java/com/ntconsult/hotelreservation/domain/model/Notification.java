package com.ntconsult.hotelreservation.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "notifications")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Notification implements GenericEntity<Notification> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_id", nullable = false)
    private Reservation reservation;

    @Column(nullable = false)
    private String message;

    @Column(nullable = false)
    private LocalDate sentDate;

}
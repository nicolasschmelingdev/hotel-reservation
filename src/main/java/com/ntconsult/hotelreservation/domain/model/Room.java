package com.ntconsult.hotelreservation.domain.model;

import com.ntconsult.hotelreservation.domain.model.enums.RoomType;
import com.ntconsult.hotelreservation.domain.model.enums.RoomTypeConverter;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "rooms")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Room implements GenericEntity<Room> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String roomNumber;

    @Convert(converter = RoomTypeConverter.class)
    @Column(nullable = false)
    private RoomType roomType;

    @Column(nullable = false)
    private Double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id", nullable = false)
    private Hotel hotel;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reservation> reservations;

}
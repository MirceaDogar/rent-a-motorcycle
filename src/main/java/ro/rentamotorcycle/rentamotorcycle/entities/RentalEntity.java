package ro.rentamotorcycle.rentamotorcycle.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "rentals")
public class RentalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "motorcycle_id", nullable = false)
    private MotorcycleEntity motorcycle;

    @Column(nullable = false, name = "pickup_date")
    private LocalDate pickupDate;

    @Column(nullable = false, name = "dropoff_date")
    private LocalDate dropoffDate;

    @Column(nullable = false, name = "pickup_location")
    private String pickupLocation;

    @Column(nullable = false, name = "dropoff_location")
    private String dropoffLocation;
}

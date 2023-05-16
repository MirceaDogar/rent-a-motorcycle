package ro.rentamotorcycle.rentamotorcycle.entities;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "motorcycles_locations")
public class MotorcycleLocationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "motorcycle_id", nullable = false)
    private MotorcycleEntity motorcycle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id", nullable = false)
    private LocationEntity location;
}

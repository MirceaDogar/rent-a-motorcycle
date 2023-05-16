package ro.rentamotorcycle.rentamotorcycle.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "motorcycles_accessories")

public class MotorcycleAccessoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "motorcycle_id", nullable = false)
    private MotorcycleEntity motorcycle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accessory_id", nullable = false)
    private AccessoryEntity accessory;


}

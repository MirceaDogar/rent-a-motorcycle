package ro.rentamotorcycle.rentamotorcycle.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "motorcycles")
public class MotorcycleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "make")
    private String make;

    @Column(name = "model")
    private String model;

    @Column(name = "year")
    private int year;

    @Column(name = "color")
    private String color;

    @Column(name = "rental_rate")
    private int rentalRate;

    @Column(name = "is_available")
    private boolean isAvailable;
    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }


}

package DanielaPetrova.RentACar.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brand;
    private String model;
    private Integer numberOfSeats;
    private Double rentPerDay;
    @OneToMany(mappedBy = "car", fetch = FetchType.LAZY)
    @JsonBackReference
    private Set<Reservation> reservationSet;

}

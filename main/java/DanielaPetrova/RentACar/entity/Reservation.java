package DanielaPetrova.RentACar.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity(name = "reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "car_id")
    @JsonManagedReference
    private Car car;
    @ManyToOne
    @JoinColumn(name="user_id")
    @JsonManagedReference
    private User user;
    private Instant startDate;

    private Instant endDate;
    private int rentalDays;
    private Double price;

}

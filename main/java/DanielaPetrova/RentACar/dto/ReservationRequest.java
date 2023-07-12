package DanielaPetrova.RentACar.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class ReservationRequest {
    @JsonFormat(shape = JsonFormat.Shape.SCALAR ,pattern="yyyy-MM-dd 'T' HH:mm:ss.SSS 'Z'", timezone = "UTC +3")
    private Instant startDate;
    @JsonFormat( shape = JsonFormat.Shape.STRING , pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ" )
    private Instant endDate;
    private Long userId;
    private Long carId;
}

package DanielaPetrova.RentACar.repository;

import DanielaPetrova.RentACar.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM reservations WHERE start_date between ?1 and ?2 AND end_date between ?1 and ?2")
    Optional<Set<Reservation>> getReservationsByPeriod(String start, String end);

    @Query(nativeQuery = true, value = "SELECT * FROM reservations WHERE car_id = ?1")
    Set<Reservation> findAllByCar(Long carId);
    @Query(nativeQuery = true, value = "SELECT * FROM reservations WHERE user_id = ?1")
    Set<Reservation> findAllByUser(Long userId);
}

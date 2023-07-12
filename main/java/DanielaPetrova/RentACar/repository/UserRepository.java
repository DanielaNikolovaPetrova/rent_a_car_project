package DanielaPetrova.RentACar.repository;

import DanielaPetrova.RentACar.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM users WHERE users.email=:email")
    User findUserByEmail(String email);
}

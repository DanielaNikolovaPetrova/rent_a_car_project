package DanielaPetrova.RentACar.service;

import DanielaPetrova.RentACar.dto.UserUpdateRequest;
import DanielaPetrova.RentACar.dto.UserRegisterRequest;
import DanielaPetrova.RentACar.dto.UserResponse;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    UserResponse saveUser(UserRegisterRequest request);
    UserResponse findById(Long id);
    UserResponse findByEmail(String email);
    UserResponse updateUser(Long id, UserUpdateRequest request);
    String changePassword(Long id, UserUpdateRequest request);
    void deleteUser(Long id);
}

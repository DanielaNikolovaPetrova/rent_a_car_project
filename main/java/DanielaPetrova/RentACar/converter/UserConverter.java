package DanielaPetrova.RentACar.converter;

import DanielaPetrova.RentACar.dto.UserRegisterRequest;
import DanielaPetrova.RentACar.dto.UserResponse;
import DanielaPetrova.RentACar.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {
    public User toUser(UserRegisterRequest request){
        return User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(request.getPassword())
                .build();
    }

    public UserResponse toResponse(User savedUser){
        return new UserResponse(
                savedUser.getFirstName(),
                savedUser.getLastName(),
                savedUser.getEmail());
    }
}

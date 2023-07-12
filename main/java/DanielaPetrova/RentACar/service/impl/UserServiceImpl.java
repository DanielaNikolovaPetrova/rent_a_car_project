package DanielaPetrova.RentACar.service.impl;

import DanielaPetrova.RentACar.converter.UserConverter;
import DanielaPetrova.RentACar.dto.UserUpdateRequest;
import DanielaPetrova.RentACar.dto.UserRegisterRequest;
import DanielaPetrova.RentACar.dto.UserResponse;
import DanielaPetrova.RentACar.entity.User;
import DanielaPetrova.RentACar.exception.NotFoundException;
import DanielaPetrova.RentACar.repository.UserRepository;
import DanielaPetrova.RentACar.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserConverter userConverter;
    private final UserRepository userRepository;

    public UserServiceImpl(UserConverter userConverter, UserRepository userRepository) {
        this.userConverter = userConverter;
        this.userRepository = userRepository;
    }


    @Override
    public UserResponse saveUser(UserRegisterRequest request) {
        User user = userConverter.toUser(request);
        User savedUser = userRepository.save(user);
        return userConverter.toResponse(savedUser);
    }

    @Override
    public UserResponse findById(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new NotFoundException("User not found")
        );
        return userConverter.toResponse(user);
    }

    @Override
    public UserResponse findByEmail(String email) {
        User user = userRepository.findUserByEmail(email);
        return userConverter.toResponse(user);
    }

    @Override
    public UserResponse updateUser(Long id, UserUpdateRequest updateRequest) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new NotFoundException("User not found")
        );
        if(updateRequest.getFirstName() != null && !updateRequest.getFirstName().isBlank()){
            user.setFirstName(updateRequest.getFirstName());
        }
        if(updateRequest.getLastName() != null && !updateRequest.getLastName().isBlank()){
            user.setLastName(updateRequest.getLastName());
        }
        if(updateRequest.getEmail() != null && !updateRequest.getEmail().isBlank()){
            user.setEmail(updateRequest.getEmail());
        }
        if(updateRequest.getPassword() != null && !updateRequest.getPassword().isBlank()){
            user.setPassword(updateRequest.getPassword());
        }
        return userConverter.toResponse(userRepository.save(user));
    }

    @Override
    public String changePassword(Long id, UserUpdateRequest updateRequest) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new NotFoundException("User not found")
        );
        if(updateRequest.getPassword() != null && !updateRequest.getPassword().isBlank()){
            user.setPassword(updateRequest.getPassword());
        }
        return "Your password has been changed";
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}

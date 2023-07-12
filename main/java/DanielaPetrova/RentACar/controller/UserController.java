package DanielaPetrova.RentACar.controller;

import DanielaPetrova.RentACar.dto.UserUpdateRequest;
import DanielaPetrova.RentACar.dto.UserRegisterRequest;
import DanielaPetrova.RentACar.dto.UserResponse;
import DanielaPetrova.RentACar.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/user")
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping("/register")
    public ResponseEntity<UserResponse> saveUser(@Valid @RequestBody UserRegisterRequest request){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userService.saveUser(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.FOUND)
                .body(userService.findById(id));
    }
    @GetMapping(path = "/email")
    public ResponseEntity<UserResponse> findByEmail(@RequestParam("email") String email){
        return ResponseEntity.status(HttpStatus.FOUND)
                .body(userService.findByEmail(email));
    }
    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long id,
                                                   @RequestBody UserUpdateRequest userRequest){
        UserResponse userResponse = userService.updateUser(id, userRequest);
        return ResponseEntity.status(HttpStatus.OK).body(userResponse);
    }
    @PutMapping("/change/{id}")
    public HttpStatus changePassword(@PathVariable Long id,
                                 @RequestBody UserUpdateRequest userRequest){
        userService.updateUser(id, userRequest);
        return HttpStatus.OK;
    }
    @DeleteMapping(path = "/{id}")
    public HttpStatus deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return HttpStatus.ACCEPTED;
    }
}

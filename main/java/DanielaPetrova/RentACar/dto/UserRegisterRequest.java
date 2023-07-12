package DanielaPetrova.RentACar.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@AllArgsConstructor
@Getter
@Setter
public class UserRegisterRequest {
    @NotBlank
    @Length(min = 3, message = "The first name must contain min 3 characters")
    private String firstName;
    private String lastName;
    @NotBlank
    @Email(message = "Enter valid email address")
    private String email;
    @NotBlank
    @Length(min = 8)
    private String password;
}

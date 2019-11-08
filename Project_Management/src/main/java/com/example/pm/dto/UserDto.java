package com.example.pm.dto;


import com.example.pm.dto.constraints.PasswordConfirmConstraint;
import com.example.pm.dto.constraints.PasswordNotEmptyConstraint;
import lombok.*;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@PasswordConfirmConstraint(
        field = "password",
        fieldMatch = "passwordConfirm",
        message = "Hasła są rózne"
)
/*@PasswordNotEmptyConstraint(
        field = "id",
        fieldMatch = "password",
        message = "Pole nie może być puste"
)*/
public class UserDto {

    private Long id;

    @Size(max = 255, message = "Pole może zawierać więcej niż 255 znaków")
    @NotEmpty(message = "Pole nie może być puste")
    private String firstName;

    @Size(max = 255, message = "Pole może zawierać więcej niż 255 znaków")
    @NotEmpty(message = "Pole nie może być puste")
    private String lastName;

    @Size(max = 255, message = "Pole może zawierać więcej niż 255 znaków")
    @Pattern(regexp = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$", message = "Podaj poprawny email!")
    private String email;

    @Size(max = 255, message = "Pole może zawierać więcej niż 255 znaków")
    private String password;

    @Size(max = 255, message = "Pole może zawierać więcej niż 255 znaków")
    private String passwordConfirm;

    private List<RoleDto> roles;

}

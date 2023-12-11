package org.demointernetshop.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Schema(name = "LoginRequest", description = "Info for authenticate")
@Getter
public class LoginRequestDto {
    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    @Schema(description = "Username of the user", example = "Igor123")
    private String username;

    @NotBlank(message = "Password is required")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$",
            message = "Password must be at least 8 characters long and include letters, numbers, and special characters")
    @Schema(description = "User's password", example = "Qwerty007!")
    private String password;

//    public CharSequence getPassword() {
//        return "123";
//    }
//
//    public String getUsername() {
//        return "Admin";
//    }
}

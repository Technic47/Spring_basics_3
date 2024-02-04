package ru.gb.springdemo.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import ru.gb.springdemo.model.UserEntity;

public class RegistrationRequestDto extends AuthenticationRequestDto {
    //RFC 5322
    @NotEmpty
    @Email(regexp = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    @Size(max = 100)
    private String email;

    public RegistrationRequestDto() {
    }

    public RegistrationRequestDto(String username, String password, String email) {
        super(username, password);
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

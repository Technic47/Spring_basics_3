package ru.gb.springdemo.api.auth;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gb.springdemo.dto.RegistrationRequestDto;
import ru.gb.springdemo.dto.UserDto;
import ru.gb.springdemo.service.UserService;

@RestController
@RequestMapping("/registration")
public class RegistrationControllerREST {
    private final UserService userService;

    public RegistrationControllerREST(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Registration")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User registered.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDto.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid request Body",
                    content = @Content)})
    @PostMapping()
    public UserDto newUser(@Valid @RequestBody RegistrationRequestDto dto) {

        UserDto registered = userService.registerNewUserAccount(dto);

        return registered;
    }
}

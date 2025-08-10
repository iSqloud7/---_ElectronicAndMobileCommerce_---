package mk.ukim.finki.eimt.lab.Web.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.eimt.lab.Model.DTO.UserDTO.*;
import mk.ukim.finki.eimt.lab.Model.Exception.UserNotFoundException;
import mk.ukim.finki.eimt.lab.Service.Application.UserApplicationService;
import mk.ukim.finki.eimt.lab.Service.Domain.UserDomainService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    private final UserApplicationService userApplicationService;
    private final UserDomainService userService;

    public UserRestController(UserApplicationService userApplicationService, UserDomainService userService) {
        this.userApplicationService = userApplicationService;
        this.userService = userService;
    }

    @Operation(
            summary = "User Registration",
            description = "Registers a new user with the provided details."
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "User registered successfully."
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Invalid input or user already exists."
                    )
            }
    )
    @PostMapping("/register")
    public ResponseEntity<DisplayUserDTO> register(@RequestBody RegisterUserDTO registerUserDTO) throws Exception {
        return this.userApplicationService.register(registerUserDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "User Login", description = "Authenticates a user using username and password, and starts a session!")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "User authenticated successfully!"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "User not found!"
                    )
            }
    )
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginUserDTO loginUserDTO) {
        return this.userApplicationService.login(loginUserDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "User Logout", description = "Logs out an authenticated user and terminates the session!")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "User logged out successfully!"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "User session not found or already logged out!"
                    )
            }
    )
    @GetMapping("/logout")
    public void logout(HttpServletRequest httpServletRequest) {
        httpServletRequest.getSession().invalidate();
    }
}
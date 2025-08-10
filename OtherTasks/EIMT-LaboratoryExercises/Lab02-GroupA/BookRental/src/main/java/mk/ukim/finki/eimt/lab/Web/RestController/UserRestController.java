package mk.ukim.finki.eimt.lab.Web.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.eimt.lab.Model.DTO.UserDTO.CreateUserDTO;
import mk.ukim.finki.eimt.lab.Model.DTO.UserDTO.DisplayUserDTO;
import mk.ukim.finki.eimt.lab.Model.DTO.UserDTO.LoginUserDTO;
import mk.ukim.finki.eimt.lab.Model.Exception.UserNotFoundException;
import mk.ukim.finki.eimt.lab.Service.Application.UserApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/user")
@Tag(name = "User API", description = "Endpoints for managing users")
public class UserRestController {

    private final UserApplicationService userApplicationService;

    public UserRestController(UserApplicationService userApplicationService) {
        this.userApplicationService = userApplicationService;
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
    public ResponseEntity<Void> register(@RequestBody CreateUserDTO createUserDTO) {
        this.userApplicationService.register(createUserDTO);

        return ResponseEntity.noContent().build();
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
    public ResponseEntity<Optional<DisplayUserDTO>> login(@RequestBody LoginUserDTO loginUserDTO,
                                                          HttpServletRequest request) {
        try {
            Optional<DisplayUserDTO> user_obj = this.userApplicationService.login(loginUserDTO.username(), loginUserDTO.password());
            request.getSession().setAttribute("user", user_obj);
            return ResponseEntity.ok(user_obj);
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
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
    public void logout(HttpServletRequest request) {
        request.getSession().invalidate();
    }
}

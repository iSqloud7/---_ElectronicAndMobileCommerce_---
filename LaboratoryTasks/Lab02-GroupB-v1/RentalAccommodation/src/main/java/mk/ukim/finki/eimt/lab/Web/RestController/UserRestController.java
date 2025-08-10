package mk.ukim.finki.eimt.lab.Web.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.eimt.lab.Model.DTO.UserDTO.CreateUserDTO;
import mk.ukim.finki.eimt.lab.Model.DTO.UserDTO.DisplayUserDTO;
import mk.ukim.finki.eimt.lab.Model.DTO.UserDTO.LoginUserDTO;
import mk.ukim.finki.eimt.lab.Model.Exception.UserNotFoundException;
import mk.ukim.finki.eimt.lab.Service.Domain.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
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
    public ResponseEntity<Void> register(@RequestBody CreateUserDTO createUserDTO) {
        this.userService.register(createUserDTO);

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
    public ResponseEntity<DisplayUserDTO> login(@RequestBody LoginUserDTO loginUserDTO,
                                                HttpServletRequest httpServletRequest) {
        try {
            DisplayUserDTO user = this.userService.login(loginUserDTO.username(), loginUserDTO.password());
            httpServletRequest.getSession().setAttribute("user", user);

            return ResponseEntity.ok(user);
        } catch (UserNotFoundException ex) {
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
    public void logout(HttpServletRequest httpServletRequest) {
        httpServletRequest.getSession().invalidate();
    }
}
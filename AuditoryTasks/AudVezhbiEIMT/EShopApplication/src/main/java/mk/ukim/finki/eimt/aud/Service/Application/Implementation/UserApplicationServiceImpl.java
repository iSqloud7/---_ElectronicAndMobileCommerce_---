package mk.ukim.finki.eimt.aud.Service.Application.Implementation;

import mk.ukim.finki.eimt.aud.Model.DTO.CreateUserDTO;
import mk.ukim.finki.eimt.aud.Model.DTO.DisplayUserDTO;
import mk.ukim.finki.eimt.aud.Model.DTO.LoginUserDTO;
import mk.ukim.finki.eimt.aud.Model.Entity.User;
import mk.ukim.finki.eimt.aud.Service.Application.UserApplicationService;
import mk.ukim.finki.eimt.aud.Service.Domain.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserApplicationServiceImpl implements UserApplicationService {

    private final UserService userService;

    public UserApplicationServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Optional<DisplayUserDTO> register(CreateUserDTO createUserDTO) {
        User user_obj = this.userService.register(
                createUserDTO.username(),
                createUserDTO.password(),
                createUserDTO.repeatPassword(),
                createUserDTO.name(),
                createUserDTO.surname(),
                createUserDTO.role()
        );

        return Optional.of(DisplayUserDTO.from(user_obj));
    }

    @Override
    public Optional<DisplayUserDTO> login(LoginUserDTO loginUserDTO) {
        return Optional.of(DisplayUserDTO.from(this.userService.login(
                loginUserDTO.username(),
                loginUserDTO.password()
        )));
    }

    @Override
    public Optional<DisplayUserDTO> findByUsername(String username) {
        return Optional.of(DisplayUserDTO.from(this.userService.findByUsername(username)));
    }
}
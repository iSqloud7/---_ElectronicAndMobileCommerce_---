package mk.ukim.finki.eimt.lab.Service.Application;

import mk.ukim.finki.eimt.lab.Model.DTO.UserDTO.*;

import java.util.Optional;

public interface UserApplicationService {

    Optional<DisplayUserDTO> register(RegisterUserDTO registerUserDTO);

    Optional<LoginResponseDTO> login(LoginUserDTO loginUserDTO);

//    void register(CreateUserDTO createUserDTO);
//
//    DisplayUserDTO login(String username, String password);
}
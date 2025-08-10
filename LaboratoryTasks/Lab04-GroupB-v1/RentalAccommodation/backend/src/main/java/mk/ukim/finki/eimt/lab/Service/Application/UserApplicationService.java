package mk.ukim.finki.eimt.lab.Service.Application;

import mk.ukim.finki.eimt.lab.Model.DTO.UserDTO.*;

import java.util.Optional;

public interface UserApplicationService {

    Optional<DisplayUserDTO> register(RegisterUserDTO registerUserDTO) throws Exception;

    Optional<LoginResponseDTO> login(LoginUserDTO loginUserDTO);
}
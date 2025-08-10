package mk.ukim.finki.eimt.aud.Service.Application;

import mk.ukim.finki.eimt.aud.Model.DTO.CreateUserDTO;
import mk.ukim.finki.eimt.aud.Model.DTO.DisplayUserDTO;
import mk.ukim.finki.eimt.aud.Model.DTO.LoginUserDTO;

import java.util.Optional;

public interface UserApplicationService {

    Optional<DisplayUserDTO> register(CreateUserDTO createUserDTO);

    Optional<DisplayUserDTO> login(LoginUserDTO loginUserDTO);

    Optional<DisplayUserDTO> findByUsername(String username);
}
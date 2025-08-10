package mk.ukim.finki.eimt.lab.Service.Domain;

import mk.ukim.finki.eimt.lab.Model.DTO.UserDTO.CreateUserDTO;
import mk.ukim.finki.eimt.lab.Model.DTO.UserDTO.DisplayUserDTO;

import java.util.Optional;

public interface UserDomainService {
    void register(CreateUserDTO createUserDTO);

    Optional<DisplayUserDTO> login(String username, String password);
}

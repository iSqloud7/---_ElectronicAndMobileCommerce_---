package mk.ukim.finki.eimt.lab.Service.Domain;

import mk.ukim.finki.eimt.lab.Model.DTO.UserDTO.*;
import mk.ukim.finki.eimt.lab.Model.Domain.User;
import mk.ukim.finki.eimt.lab.Model.Enumerations.UserRole;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserDomainService extends UserDetailsService {

    Optional<User> register(String username, String password, String name, String surname, UserRole userRole);

    Optional<LoginResponseDTO> login(User user);

//    void register(CreateUserDTO createUserDTO);
//
//    DisplayUserDTO login(String username, String password);
}
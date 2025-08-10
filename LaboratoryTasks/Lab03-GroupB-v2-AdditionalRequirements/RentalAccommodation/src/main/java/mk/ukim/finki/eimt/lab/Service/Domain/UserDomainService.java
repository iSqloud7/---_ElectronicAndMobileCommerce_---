package mk.ukim.finki.eimt.lab.Service.Domain;

import mk.ukim.finki.eimt.lab.Model.DTO.UserDTO.*;
import mk.ukim.finki.eimt.lab.Model.Entities.User;
import mk.ukim.finki.eimt.lab.Model.Enumerations.UserRole;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserDomainService extends UserDetailsService {

    Optional<User> register(String username, String password, String name, String surname, UserRole userRole) throws Exception;

    Optional<LoginResponseDTO> login(User user);
}
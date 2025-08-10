package mk.ukim.finki.eimt.aud.Service.Domain;

import mk.ukim.finki.eimt.aud.Model.Entity.User;
import mk.ukim.finki.eimt.aud.Model.Enumerations.Role;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User register(String username, String password, String repeatPassword, String name, String surname, Role role);

    User login(String username, String password);

    User findByUsername(String username);
}
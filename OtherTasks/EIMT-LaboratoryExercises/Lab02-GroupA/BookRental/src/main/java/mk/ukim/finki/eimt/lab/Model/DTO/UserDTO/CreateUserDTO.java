package mk.ukim.finki.eimt.lab.Model.DTO.UserDTO;

import mk.ukim.finki.eimt.lab.Model.Enumerations.Role;
import mk.ukim.finki.eimt.lab.Model.User;

public record CreateUserDTO(
        String username,
        String password,
        String name,
        String surname,
        Role role) {

    public User toUser() {
        return new User(username, password, name, surname, role);
    }
}

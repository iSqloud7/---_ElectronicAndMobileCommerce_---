package mk.ukim.finki.eimt.lab.Model.DTO.UserDTO;

import mk.ukim.finki.eimt.lab.Model.Enumerations.UserRole;
import mk.ukim.finki.eimt.lab.Model.Entities.User;

public record CreateUserDTO(
        String username,
        String password,
        String name,
        String surname,
        UserRole userRole
) {
    public User toUser() {
        return new User(username, password, name, surname, userRole);
    }
}
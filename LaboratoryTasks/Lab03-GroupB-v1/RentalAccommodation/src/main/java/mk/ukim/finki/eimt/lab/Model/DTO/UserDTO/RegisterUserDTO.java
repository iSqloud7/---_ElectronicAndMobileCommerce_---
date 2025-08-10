package mk.ukim.finki.eimt.lab.Model.DTO.UserDTO;

import mk.ukim.finki.eimt.lab.Model.Domain.User;
import mk.ukim.finki.eimt.lab.Model.Enumerations.UserRole;

public record RegisterUserDTO(
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

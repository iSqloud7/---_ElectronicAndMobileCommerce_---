package mk.ukim.finki.eimt.lab.Model.DTO.UserDTO;

import mk.ukim.finki.eimt.lab.Model.Enumerations.UserRole;
import mk.ukim.finki.eimt.lab.Model.Domain.User;

public record CreateUserDTO(
        String username,
        String password,
        UserRole userRole
) {
    public User toUser() {
        return new User(username, password, userRole);
    }
}
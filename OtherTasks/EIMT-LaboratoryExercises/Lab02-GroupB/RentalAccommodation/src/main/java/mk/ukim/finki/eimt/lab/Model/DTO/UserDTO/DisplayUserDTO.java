package mk.ukim.finki.eimt.lab.Model.DTO.UserDTO;

import mk.ukim.finki.eimt.lab.Model.Enumerations.UserRole;
import mk.ukim.finki.eimt.lab.Model.Domain.User;

public record DisplayUserDTO(
        String username,
        String password,
        UserRole userRole
) {
    public static DisplayUserDTO from(User user) {
        return new DisplayUserDTO(
                user.getUsername(),
                user.getPassword(),
                user.getUserRole()
        );
    }
}
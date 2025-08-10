package mk.ukim.finki.eimt.lab.Model.DTO.UserDTO;

import mk.ukim.finki.eimt.lab.Model.Enumerations.Role;
import mk.ukim.finki.eimt.lab.Model.Entities.User;

public record DisplayUserDTO(
        String username,
        String name,
        String surname,
        Role role) {

    public static DisplayUserDTO fromUser(User user) {
        return new DisplayUserDTO(
                user.getUsername(),
                user.getName(),
                user.getSurname(),
                user.getRole()
        );
    }
}

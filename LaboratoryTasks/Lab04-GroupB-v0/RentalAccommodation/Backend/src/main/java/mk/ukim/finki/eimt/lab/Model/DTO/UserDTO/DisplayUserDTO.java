package mk.ukim.finki.eimt.lab.Model.DTO.UserDTO;

import mk.ukim.finki.eimt.lab.Model.Enumerations.UserRole;
import mk.ukim.finki.eimt.lab.Model.Entities.User;

public record DisplayUserDTO(
        Long ID,
        String username,
        String password,
        String name,
        String surname,
        UserRole userRole
) {
    public static DisplayUserDTO from(User user) {
        return new DisplayUserDTO(
                user.getID(),
                user.getUsername(),
                user.getPassword(),
                user.getName(),
                user.getSurname(),
                user.getUserRole()
        );
    }
}
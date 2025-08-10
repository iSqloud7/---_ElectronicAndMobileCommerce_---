package mk.ukim.finki.eimt.aud.Model.DTO;

import mk.ukim.finki.eimt.aud.Model.Entity.User;
import mk.ukim.finki.eimt.aud.Model.Enumerations.Role;

public record DisplayUserDTO(

        String username,
        String name,
        String surname,
        Role role
) {
    public static DisplayUserDTO from(User user) {
        return new DisplayUserDTO(
                user.getUsername(),
                user.getName(),
                user.getSurname(),
                user.getRole()
        );
    }

    public User toUser(){
        return new User(username, name, surname, role.name());
    }
}
package mk.ukim.finki.eimt.aud.Model.DTO;

import mk.ukim.finki.eimt.aud.Model.Entity.User;
import mk.ukim.finki.eimt.aud.Model.Enumerations.Role;

public record CreateUserDTO(

        String username,
        String password,
        String repeatPassword,
        String name,
        String surname,
        Role role
) {
    public User toUser() {
        return new User(username, password, name, surname, role);
    }
}
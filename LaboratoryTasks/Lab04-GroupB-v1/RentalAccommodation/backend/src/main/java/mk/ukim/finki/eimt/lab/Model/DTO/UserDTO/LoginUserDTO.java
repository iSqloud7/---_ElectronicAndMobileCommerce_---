package mk.ukim.finki.eimt.lab.Model.DTO.UserDTO;

import mk.ukim.finki.eimt.lab.Model.Entities.User;

public record LoginUserDTO(
        String username,
        String password
) {

    public User to() {
        return new User(username, password);
    }
}

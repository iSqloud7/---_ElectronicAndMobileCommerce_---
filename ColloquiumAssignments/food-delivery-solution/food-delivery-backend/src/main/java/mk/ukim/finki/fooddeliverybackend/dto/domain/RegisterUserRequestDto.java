package mk.ukim.finki.fooddeliverybackend.dto.domain;

import mk.ukim.finki.fooddeliverybackend.model.domain.User;

public record RegisterUserRequestDto(
        String username,
        String password,
        String name,
        String surname,
        String email
) {

    public User toUser() {
        return new User(username, password, name, surname, email);
    }

}

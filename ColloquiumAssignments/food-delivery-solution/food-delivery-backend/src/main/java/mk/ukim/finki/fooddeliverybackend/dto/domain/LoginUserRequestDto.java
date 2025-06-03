package mk.ukim.finki.fooddeliverybackend.dto.domain;

public record LoginUserRequestDto(
        String username,
        String password
) {
}

package mk.ukim.finki.fooddeliverybackend.service.application;

import mk.ukim.finki.fooddeliverybackend.dto.domain.LoginUserRequestDto;
import mk.ukim.finki.fooddeliverybackend.dto.domain.LoginUserResponseDto;
import mk.ukim.finki.fooddeliverybackend.dto.domain.RegisterUserRequestDto;
import mk.ukim.finki.fooddeliverybackend.dto.domain.RegisterUserResponseDto;

import java.util.Optional;

public interface UserApplicationService {
    Optional<RegisterUserResponseDto> register(RegisterUserRequestDto registerUserRequestDto);

    Optional<LoginUserResponseDto> login(LoginUserRequestDto loginUserRequestDto);

    Optional<RegisterUserResponseDto> findByUsername(String username);
}

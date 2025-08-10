package mk.ukim.finki.eimt.lab.Service.Application.Implementation;

import mk.ukim.finki.eimt.lab.Model.DTO.UserDTO.*;
import mk.ukim.finki.eimt.lab.Service.Application.UserApplicationService;
import mk.ukim.finki.eimt.lab.Service.Domain.UserDomainService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserApplicationServiceImpl implements UserApplicationService {

    private final UserDomainService userDomainService;

    public UserApplicationServiceImpl(UserDomainService userDomainService) {
        this.userDomainService = userDomainService;
    }

    @Override
    public Optional<DisplayUserDTO> register(RegisterUserDTO registerUserDTO) throws Exception {
        return this.userDomainService.register(
                registerUserDTO.username(),
                registerUserDTO.password(),
                registerUserDTO.name(),
                registerUserDTO.surname(),
                registerUserDTO.userRole()
        ).map(DisplayUserDTO::from);
    }

    @Override
    public Optional<LoginResponseDTO> login(LoginUserDTO loginUserDTO) {
        return this.userDomainService.login(
                loginUserDTO.to()
        );
    }
}
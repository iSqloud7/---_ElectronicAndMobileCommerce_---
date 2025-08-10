package mk.ukim.finki.eimt.lab.Service.Application.Implementation;

import mk.ukim.finki.eimt.lab.Model.DTO.UserDTO.CreateUserDTO;
import mk.ukim.finki.eimt.lab.Model.DTO.UserDTO.DisplayUserDTO;
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
    public void register(CreateUserDTO createUserDTO) {
        this.userDomainService.register(createUserDTO);
    }

    @Override
    public Optional<DisplayUserDTO> login(String username, String password) {
        return this.userDomainService.login(username, password);
    }
}

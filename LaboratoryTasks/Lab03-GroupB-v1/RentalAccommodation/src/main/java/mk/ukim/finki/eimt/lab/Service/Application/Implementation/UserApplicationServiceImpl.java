package mk.ukim.finki.eimt.lab.Service.Application.Implementation;

import mk.ukim.finki.eimt.lab.Model.DTO.UserDTO.*;
import mk.ukim.finki.eimt.lab.Repository.ReservationListRepository;
import mk.ukim.finki.eimt.lab.Repository.UserRepository;
import mk.ukim.finki.eimt.lab.Service.Application.UserApplicationService;
import mk.ukim.finki.eimt.lab.Service.Domain.UserDomainService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserApplicationServiceImpl implements UserApplicationService {

    private final UserDomainService userDomainService;
    private final UserRepository userRepository;
    private final ReservationListRepository reservationListRepository;

    public UserApplicationServiceImpl(UserDomainService userDomainService, UserRepository userRepository, ReservationListRepository reservationListRepository) {
        this.userDomainService = userDomainService;
        this.userRepository = userRepository;
        this.reservationListRepository = reservationListRepository;
    }

    @Override
    public Optional<DisplayUserDTO> register(RegisterUserDTO registerUserDTO) {
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

//    public void register(CreateUserDTO createUserDTO) {
//        ReservationList reservationList = new ReservationList();
//        this.reservationListRepository.save(reservationList);
//
//        User user = createUserDTO.toUser();
//        user.setReservationList(reservationList);
//        this.userRepository.save(user);
//    }
//
//    public DisplayUserDTO login(String username, String password) {
//        Optional<User> user = this.userRepository.findByUsername(username);
//
//        if (user.isEmpty()) {
//            throw new UserNotFoundException();
//        }
//
//        if (!user.get().getPassword().equals(password)) {
//            throw new UserNotFoundException();
//        }
//
//        return DisplayUserDTO.from(user.get());
//    }
}
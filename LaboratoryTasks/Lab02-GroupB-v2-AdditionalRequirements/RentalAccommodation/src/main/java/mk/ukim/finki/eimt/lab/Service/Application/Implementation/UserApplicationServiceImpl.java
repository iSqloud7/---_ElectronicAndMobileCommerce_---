package mk.ukim.finki.eimt.lab.Service.Application.Implementation;

import mk.ukim.finki.eimt.lab.Model.DTO.UserDTO.CreateUserDTO;
import mk.ukim.finki.eimt.lab.Model.DTO.UserDTO.DisplayUserDTO;
import mk.ukim.finki.eimt.lab.Model.Domain.ReservationList;
import mk.ukim.finki.eimt.lab.Model.Domain.User;
import mk.ukim.finki.eimt.lab.Model.Exception.UserNotFoundException;
import mk.ukim.finki.eimt.lab.Repository.ReservationListRepository;
import mk.ukim.finki.eimt.lab.Repository.UserRepository;
import mk.ukim.finki.eimt.lab.Service.Application.UserApplicationService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserApplicationServiceImpl implements UserApplicationService {

    private final UserRepository userRepository;
    private final ReservationListRepository reservationListRepository;

    public UserApplicationServiceImpl(UserRepository userRepository, ReservationListRepository reservationListRepository) {
        this.userRepository = userRepository;
        this.reservationListRepository = reservationListRepository;
    }

    public void register(CreateUserDTO createUserDTO) {
        ReservationList reservationList = new ReservationList();
        this.reservationListRepository.save(reservationList);

        User user = createUserDTO.toUser();
        user.setReservationList(reservationList);
        this.userRepository.save(user);
    }

    public DisplayUserDTO login(String username, String password) {
        Optional<User> user = this.userRepository.findByUsername(username);

        if (user.isEmpty()) {
            throw new UserNotFoundException();
        }

        if (!user.get().getPassword().equals(password)) {
            throw new UserNotFoundException();
        }

        return DisplayUserDTO.from(user.get());
    }
}
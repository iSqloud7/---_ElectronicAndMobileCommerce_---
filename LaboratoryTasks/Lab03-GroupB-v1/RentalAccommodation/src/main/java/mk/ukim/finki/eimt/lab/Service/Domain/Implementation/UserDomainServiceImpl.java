package mk.ukim.finki.eimt.lab.Service.Domain.Implementation;

import mk.ukim.finki.eimt.lab.Config.JWT.JWTHelper;
import mk.ukim.finki.eimt.lab.Model.DTO.UserDTO.LoginResponseDTO;
import mk.ukim.finki.eimt.lab.Model.Domain.User;
import mk.ukim.finki.eimt.lab.Model.Enumerations.UserRole;
import mk.ukim.finki.eimt.lab.Repository.ReservationListRepository;
import mk.ukim.finki.eimt.lab.Repository.UserRepository;
import mk.ukim.finki.eimt.lab.Service.Domain.UserDomainService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDomainServiceImpl implements UserDomainService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTHelper jwtHelper;
    private final ReservationListRepository reservationListRepository;

    public UserDomainServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JWTHelper jwtHelper, ReservationListRepository reservationListRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtHelper = jwtHelper;
        this.reservationListRepository = reservationListRepository;
    }

    @Override
    public Optional<User> register(String username, String password, String name, String surname, UserRole userRole) {
        Optional<User> user_opt = this.userRepository.findByUsername(username);

        if (user_opt.isEmpty()) {
            User user_obj = new User(username, this.passwordEncoder.encode(password), name, surname, userRole);
            return Optional.of(this.userRepository.save(user_obj));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<LoginResponseDTO> login(User user) {
        Optional<User> user_opt = this.userRepository.findByUsername(user.getUsername());

        if (user_opt.isPresent() && this.passwordEncoder.matches(user.getPassword(), user_opt.get().getPassword())) {
            String jwt = jwtHelper.generateToken(user);

            return Optional.of(new LoginResponseDTO(jwt));
        }

        return Optional.empty();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
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
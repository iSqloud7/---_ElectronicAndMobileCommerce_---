package mk.ukim.finki.eimt.lab.Service.Domain.Implementation;

import mk.ukim.finki.eimt.lab.Config.JWT.JwtHelper;
import mk.ukim.finki.eimt.lab.Model.DTO.UserDTO.LoginResponseDTO;
import mk.ukim.finki.eimt.lab.Model.Entities.ReservationList;
import mk.ukim.finki.eimt.lab.Model.Entities.User;
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
    private final JwtHelper jwtHelper;
    private final ReservationListRepository reservationListRepository;

    public UserDomainServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtHelper jwtHelper, ReservationListRepository reservationListRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtHelper = jwtHelper;
        this.reservationListRepository = reservationListRepository;
    }

    @Override
    public Optional<User> register(String username, String password, String name, String surname, UserRole userRole) throws Exception {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            throw new Exception();
        }

        if (this.userRepository.findByUsername(username).isPresent()) {
            throw new Exception();
        }

        ReservationList reservationList = new ReservationList();
        this.reservationListRepository.save(reservationList);

        Optional<User> user_opt = this.userRepository.findByUsername(username);

        if (user_opt.isEmpty()) {
            User user_obj = new User(username, this.passwordEncoder.encode(password), name, surname, userRole);
            user_obj.setReservationList(reservationList);
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
}
package mk.ukim.finki.eimtprivatelessons.Security;

import mk.ukim.finki.eimtprivatelessons.Model.User;
import mk.ukim.finki.eimtprivatelessons.Repository.UserRepository;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomUsernamePasswordAuthenticationProvider implements AuthenticationProvider {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public CustomUsernamePasswordAuthenticationProvider(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // AuthenticationProvider, класа за автентикација
    // - дали username и password од формата соодвестуваат со username и password од базата.

    // Логика односно како да се изврши автентикацијата
    // - дали username и password од формата соодвестуваат со username и password од базата.
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName(); // username од формата
        String password = authentication.getCredentials().toString(); // password од формата

        // Вчитување на корисникот од базата.
        Optional<User> user_obj = this.userRepository.findByUsername(username);

        if (user_obj.isEmpty()) {
            throw new BadCredentialsException("Username not valid!");
        }

        boolean passwordMatches = this.passwordEncoder.matches(password, user_obj.get().getPassword());

        if (!passwordMatches) {
            throw new BadCredentialsException("Password not valid!");
        }

        // Пристап до најавениот user за понатака во некои сервиси.
        // SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // UsernamePasswordAuthenticationToken -> AbstractAuthenticationToken -> Authentication
        // Враќање на user објектот
        return new UsernamePasswordAuthenticationToken(
                user_obj.get(),
                user_obj.get().getPassword(),
                user_obj.get().getAuthorities()
        );

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}

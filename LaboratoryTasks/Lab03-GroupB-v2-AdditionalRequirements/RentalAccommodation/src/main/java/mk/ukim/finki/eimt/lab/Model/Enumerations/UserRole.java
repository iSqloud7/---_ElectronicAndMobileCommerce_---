package mk.ukim.finki.eimt.lab.Model.Enumerations;

import org.springframework.security.core.GrantedAuthority;

public enum UserRole implements GrantedAuthority {

    USER, HOST;

    @Override
    public String getAuthority() {
        return name();
    }
}

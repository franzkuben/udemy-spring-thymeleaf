package at.kuben.udemyspring.security;

import at.kuben.udemyspring.db.BenutzerRepository;
import at.kuben.udemyspring.model.Benutzer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class KursUserDetailService implements UserDetailsService {

    @Autowired
    private BenutzerRepository benutzerRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<Benutzer> maybeUser = benutzerRepository.findBenutzerByBenutzername(s);
        if(!maybeUser.isPresent()) {
            throw new UsernameNotFoundException(s);
        }

        return toUserDetails(maybeUser.get());
    }

    private UserDetails toUserDetails(Benutzer b) {
        User.UserBuilder userBuilder = User.withUsername(b.getBenutzername()).password(b.getPasswort());
        return userBuilder.roles().build();
    }
}

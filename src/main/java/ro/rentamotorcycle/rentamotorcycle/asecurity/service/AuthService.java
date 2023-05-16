package ro.rentamotorcycle.rentamotorcycle.asecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    public AuthService(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder){
        this.userDetailsService=userDetailsService;
        this.passwordEncoder=passwordEncoder;
    }
    public boolean login(String email, String password){
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        if (userDetails == null) {
            return false;
        }

        return passwordEncoder.matches(password, userDetails.getPassword());
    }
}

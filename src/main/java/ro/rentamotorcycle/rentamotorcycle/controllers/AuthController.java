package ro.rentamotorcycle.rentamotorcycle.controllers;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.rentamotorcycle.rentamotorcycle.asecurity.service.AuthService;
import ro.rentamotorcycle.rentamotorcycle.asecurity.service.LoginRequest;
import ro.rentamotorcycle.rentamotorcycle.dto.RegistrationRequest;
import ro.rentamotorcycle.rentamotorcycle.emailservice.EmailSenderService;
import ro.rentamotorcycle.rentamotorcycle.entities.Role;
import ro.rentamotorcycle.rentamotorcycle.entities.UserEntity;
import ro.rentamotorcycle.rentamotorcycle.service.UserService;

import javax.mail.MessagingException;
import java.io.IOException;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private final UserService userService;
    @Autowired
    private final EmailSenderService emailSenderService;
    @Autowired
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private final AuthService authService;
    @Autowired
    public AuthController(UserService userService, PasswordEncoder passwordEncoder, EmailSenderService emailSenderService,AuthService authService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.emailSenderService = emailSenderService;
        this.authService=authService;
    }

    @PostMapping("/register")
    @ApiOperation(value = "Create a registration.")
    public ResponseEntity<?> registerUser(@RequestBody RegistrationRequest registrationRequest) throws MessagingException, jakarta.mail.MessagingException, IOException {

        if(userService.existsByEmail(registrationRequest.getEmail())) {
            return ResponseEntity.badRequest().body("Email already taken!");
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setFullName(registrationRequest.getFullName());
        userEntity.setEmail(registrationRequest.getEmail());
        userEntity.setPhoneNumber(registrationRequest.getPhoneNumber());
        userEntity.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
        userEntity.setRole(Role.USER);

        userService.saveUser(userEntity);

        emailSenderService.sendWelcomeEmail(registrationRequest.getEmail(), registrationRequest.getFullName());

        return ResponseEntity.ok("User registered successfully!");
    }

    @PostMapping("/login")
    @ApiOperation(value = "Login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        String username = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        if (authService.login(username, password)) {
            return ResponseEntity.ok("Login successful!");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }


}


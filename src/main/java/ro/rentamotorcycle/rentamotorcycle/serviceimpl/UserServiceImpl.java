package ro.rentamotorcycle.rentamotorcycle.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ro.rentamotorcycle.rentamotorcycle.entities.RatingEntity;
import ro.rentamotorcycle.rentamotorcycle.entities.UserEntity;
import ro.rentamotorcycle.rentamotorcycle.exception.ResourceNotFoundException;
import ro.rentamotorcycle.rentamotorcycle.exception.UnauthorizedException;
import ro.rentamotorcycle.rentamotorcycle.repositories.MotorcycleRepository;
import ro.rentamotorcycle.rentamotorcycle.repositories.RatingRepository;
import ro.rentamotorcycle.rentamotorcycle.repositories.UserRepository;
import ro.rentamotorcycle.rentamotorcycle.service.UserService;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final MotorcycleRepository motorcycleRepository;
    @Autowired
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private final RatingRepository ratingRepository;
    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder,RatingRepository ratingRepository,MotorcycleRepository motorcycleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.ratingRepository = ratingRepository;
        this.motorcycleRepository = motorcycleRepository;
    }


    @Override
    public UserEntity createUser(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    @Override
    public UserEntity getUserById(Integer id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
    }

    @Override
    public UserEntity updateUser(int id, UserEntity userEntity) {
        Optional<UserEntity> optionalUser = userRepository.findById(id);
        if(optionalUser.isPresent()){
            UserEntity existingUser = optionalUser.get();
            existingUser.setFullName(userEntity.getFullName());
            existingUser.setEmail(userEntity.getEmail());
            existingUser.setPassword(userEntity.getPassword());
            existingUser.setPhoneNumber(userEntity.getPhoneNumber());
            return userRepository.save(existingUser);
        }else{
            return null;
        }
    }

    @Override
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return (List<UserEntity>) userRepository.findAll();
    }

    @Override
    public UserEntity getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new UnauthorizedException("User not authenticated");
        }
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    @Override
    public UserEntity saveUser(UserEntity userEntity) {
            userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
            return userRepository.save(userEntity);
        }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public void addRating(Integer motorcycle, Integer user, Integer rating) {
        RatingEntity ratingEntity = new RatingEntity();
        ratingEntity.setMotorcycle(motorcycleRepository.findById(motorcycle).orElseThrow());
        ratingEntity.setUser(userRepository.findById(user).orElseThrow());
        ratingEntity.setRating(rating);
        ratingEntity.setCreatedAt(new Date());
        ratingEntity.setUpdatedAt(new Date());
        ratingRepository.save(ratingEntity);
    }
}


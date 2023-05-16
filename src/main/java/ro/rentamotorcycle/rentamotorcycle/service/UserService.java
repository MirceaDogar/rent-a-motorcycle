package ro.rentamotorcycle.rentamotorcycle.service;

import org.apache.catalina.User;
import ro.rentamotorcycle.rentamotorcycle.entities.UserEntity;

import java.util.List;

public interface UserService {
    UserEntity createUser(UserEntity userEntity);
    UserEntity getUserById(Integer id);
    UserEntity updateUser(int id, UserEntity userEntity);
    void deleteUser(Integer id);
    List<UserEntity> getAllUsers();
    UserEntity getCurrentUser();
    UserEntity saveUser(UserEntity userEntity);
    boolean existsByEmail(String email);
    void addRating(Integer motorcycle, Integer user, Integer rating);
}

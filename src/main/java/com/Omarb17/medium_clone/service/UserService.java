package com.Omarb17.medium_clone.service;

import com.Omarb17.medium_clone.entity.Story;
import com.Omarb17.medium_clone.entity.User;
import com.Omarb17.medium_clone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User addNewUser(User user) {return userRepository.save(user);}

    public void deleteUser(Long id) {userRepository.deleteById(id);}

    public Optional<User> updateUser(Long id, String name, String email) {
        return userRepository.findById(id).map( user -> {
            user.setName(name);
            user.setEmail(email);
            return userRepository.save(user);
        });
    }

}

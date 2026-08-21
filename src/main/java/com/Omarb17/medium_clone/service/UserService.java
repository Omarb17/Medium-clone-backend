package com.Omarb17.medium_clone.service;

import com.Omarb17.medium_clone.exception.UserNotFoundException;
import com.Omarb17.medium_clone.mapper.UserMapper;
import com.Omarb17.medium_clone.model.dto.request.UserRequestDto;
import com.Omarb17.medium_clone.model.entity.User;
import com.Omarb17.medium_clone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public User getUserById(Long id) {return userRepository.findById(id)
            .orElseThrow(() -> new UserNotFoundException(
                    "User with id " + id + " not found"
            ));
    }

    public User addNewUser(UserRequestDto userRequestDto) {
        User user = userMapper.toEntity(userRequestDto);
        return userRepository.save(user);}

    public boolean deleteUser(Long id) {
        return userRepository.findById(id)
                .map(user -> {
                    userRepository.deleteById(id);
                    return true;
                })
                .orElse(false);
    }

    public Optional<User> updateUser(Long id, String name, String email) {
        return userRepository.findById(id).map( user -> {
            user.setName(name);
            user.setEmail(email);
            return userRepository.save(user);
        });
    }

}

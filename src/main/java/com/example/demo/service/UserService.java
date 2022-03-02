package com.example.demo.service;

import com.example.demo.error.UserNotFoundException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUserById(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    public Iterable<User> getUsersByName(String name) {
        if (name == null) {
            return userRepository.findAll();
        }
        return userRepository.findByName(name);
    }

    public User createUser(User newUser) {
        return userRepository.save(newUser);
    }

    public void updateSalaryByUserId(String id, long salary) {
        if (!userRepository.findById(id).isPresent()) {
            throw new UserNotFoundException(id);
        } else userRepository.updateSalaryByUserId(id, salary);
    }

    public User updateUserById(String id, User updatedUser) {
        return userRepository.findById(id)
                .map(user -> {
                        user.setName(updatedUser.getName());
                        user.setLastName(updatedUser.getLastName());
                        user.setMobile(updatedUser.getMobile());
                        return userRepository.save(user);
                    })
                .orElseGet(() -> {
                    updatedUser.setId(id);
                    return userRepository.save(updatedUser);
                });
    }

    public void deleteUserById(String id) {
        if (!userRepository.findById(id).isPresent()) {
            throw new UserNotFoundException(id);
        } else userRepository.deleteById(id);
    }
}

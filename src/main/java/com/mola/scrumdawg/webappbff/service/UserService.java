package com.mola.scrumdawg.webappbff.service;

import com.mola.scrumdawg.webappbff.exception.ResourceNotFoundException;
import com.mola.scrumdawg.webappbff.model.User;
import com.mola.scrumdawg.webappbff.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUserById(String id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found.", 404));
    }

    public void saveUser(User user){
        userRepository.save(user);
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }
}

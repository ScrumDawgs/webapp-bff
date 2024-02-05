package com.mola.scrumdawg.webappbff.service;

import com.mola.scrumdawg.webappbff.BaseIT;
import com.mola.scrumdawg.webappbff.exception.ResourceNotFoundException;
import com.mola.scrumdawg.webappbff.model.User;
import com.mola.scrumdawg.webappbff.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserServiceIT extends BaseIT {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;



    @BeforeEach
    public void setUp(){
        userRepository.deleteAll();
    }

    @Test
    public void testSaveAndFindUser(){
        User expectedUser = User.builder()
                .userid(nextString())
                .username(nextString())
                .firstname(nextString())
                .lastname(nextString())
                .email(nextString())
                .build();

        userService.saveUser(expectedUser);

        User actualUser = userService.getUserById(expectedUser.getUserid());

        assertThat(actualUser).isEqualTo(expectedUser);
    }

    @Test
    public void testDeleteUserAfterSaving(){
        User expectedUser = User.builder()
                .userid(nextString())
                .username(nextString())
                .firstname(nextString())
                .lastname(nextString())
                .email(nextString())
                .build();

        userService.saveUser(expectedUser);

        User foundUser = userService.getUserById(expectedUser.getUserid());

        assertThat(foundUser).isNotNull();

        userService.deleteUser(expectedUser);

        assertThrows(ResourceNotFoundException.class, () -> {
           userService.getUserById(expectedUser.getUserid());
        });
    }

}
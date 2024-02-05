package com.mola.scrumdawg.webappbff.repository;

import com.mola.scrumdawg.webappbff.BaseIT;
import com.mola.scrumdawg.webappbff.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;


class UserRepositoryIT extends BaseIT {

    @Autowired
    private UserRepository userRepository;

    private User expectedUser;

    @BeforeEach
    void setUp() {
        expectedUser = User.builder()
                .userid(nextString())
                .username(nextString())
                .firstname(nextString())
                .lastname(nextString())
                .email(nextString())
                .build();

        userRepository.save(expectedUser);
    }

    @Test
    void testFindById() {
        User actualUser = userRepository.findById(expectedUser.getUserid())
                .orElseThrow(() -> new RuntimeException("Test failed. Could not find Test-User in Redis Cache."));
        assertNotNull(actualUser);
        assertEquals(expectedUser.getUserid(), actualUser.getUserid());
        assertEquals(expectedUser.getUsername(), actualUser.getUsername());
        assertEquals(expectedUser.getFirstname(), actualUser.getFirstname());
        assertEquals(expectedUser.getLastname(), actualUser.getLastname());
        assertEquals(expectedUser.getEmail(), actualUser.getEmail());
    }
    

}
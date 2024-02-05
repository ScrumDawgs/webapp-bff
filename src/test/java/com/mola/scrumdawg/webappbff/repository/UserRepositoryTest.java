package com.mola.scrumdawg.webappbff.repository;

import com.mola.scrumdawg.webappbff.model.User;
import com.redis.testcontainers.RedisContainer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Testcontainers
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private User expectedUser;

    private int currentBaseValue = 0;


    @Container
    static RedisContainer redisContainer = new RedisContainer(
            DockerImageName.parse("redis:6-alpine")
    ).withExposedPorts(6379);


    @DynamicPropertySource
    static void redisProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.redis.host", redisContainer::getHost);
        registry.add("spring.redis.port", redisContainer::getFirstMappedPort);
    }

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
    
    private String nextString(){
        return String.valueOf(currentBaseValue++);
    }
    
    private Long nextLong(){
        return (long) currentBaseValue++;
    }

    private Integer nextInt(){
        return currentBaseValue++;
    }

    private Double nextDouble(){
        return (double) currentBaseValue++;
    }
    private boolean nextBoolean(){
        return currentBaseValue++ % 2 == 0;
    }
}
package com.mola.scrumdawg.webappbff;

import com.redis.testcontainers.RedisContainer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@SpringBootTest
@ActiveProfiles("test")
@Testcontainers
public class BaseIT {

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

    protected String nextString(){
        return String.valueOf(currentBaseValue++);
    }

    protected Long nextLong(){
        return (long) currentBaseValue++;
    }

    protected Integer nextInt(){
        return currentBaseValue++;
    }

    protected Double nextDouble(){
        return (double) currentBaseValue++;
    }
    protected boolean nextBoolean(){
        return currentBaseValue++ % 2 == 0;
    }

}

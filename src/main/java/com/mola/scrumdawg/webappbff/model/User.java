package com.mola.scrumdawg.webappbff.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Data
@Builder
@RedisHash("users")
public class User implements Serializable {

    @Id
    private String userid;
    private String username;
    private String firstname;
    private String lastname;
    private String email;


}

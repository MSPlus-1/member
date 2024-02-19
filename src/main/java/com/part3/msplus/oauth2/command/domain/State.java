package com.part3.msplus.oauth2.command.domain;

import jakarta.persistence.Id;
import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;

import java.util.UUID;

@Getter
@RedisHash(value = "state", timeToLive = 120)
public class State {

    @Id
    private final UUID id;

    private State(UUID id) {
        this.id = id;
    }

    public static State generate() {
        return new State(UUID.randomUUID());
    }
}

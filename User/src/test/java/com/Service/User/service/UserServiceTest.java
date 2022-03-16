package com.Service.User.service;

import com.Service.User.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    @Test
    void getUserList() {
        User user = new User();
        user.setName("Mukesh");
        user.setUserID(3);

        Flux<User> listOfSchool= Flux.just(user);
        StepVerifier.create(listOfSchool)
                .consumeNextWith( response -> {
                            Assertions.assertEquals("Mukesh", response.getName());
                            Assertions.assertEquals(3, response.getUserID());
                        }
                ).verifyComplete();
    }

    @Test
    void getUserByID() {
        User user = new User();
        user.setName("Mukesh");
        user.setUserID(3);
        Mono<User> schoolMono = Mono.just(user);
        StepVerifier.create(schoolMono)
                .consumeNextWith( response -> {
                            Assertions.assertEquals("Mukesh", response.getName());
                            Assertions.assertEquals(3, response.getUserID());
                        }
                ).verifyComplete();
    }
}
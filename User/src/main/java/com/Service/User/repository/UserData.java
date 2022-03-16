package com.Service.User.repository;

import com.Service.User.model.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface UserData extends ReactiveMongoRepository<User,Integer> {
    Flux<User> findByClaassCode(int claassCode);
}

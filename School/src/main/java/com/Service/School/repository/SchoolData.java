package com.Service.School.repository;

import com.Service.School.model.School;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolData extends ReactiveMongoRepository<School,Integer> {

}

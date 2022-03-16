package com.Service.School.service;

import com.Service.School.model.Claass;
import com.Service.School.model.School;
import com.Service.School.repository.SchoolData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

class SchoolServiceTest {

    @Autowired
    SchoolService schoolService;


    @Test
    void getSchoolList() {
        School school= new School();

        school.setSchoolName("Karan Public School");
        school.setSchoolID(3);

        Flux<School> listOfSchool= Flux.just(school);
        StepVerifier.create(listOfSchool)
                .consumeNextWith( response -> {
                            Assertions.assertEquals("Karan Public School", response.getSchoolName());
                            Assertions.assertEquals(3, response.getSchoolID());
                        }
                ).verifyComplete();
    }

    @Test
    void getSchoolByID(){
        School school = new School();
        school.setSchoolName("Karan Public School");
        school.setSchoolID(3);
        Mono<School>  schoolMono = Mono.just(school);
        StepVerifier.create(schoolMono)
                .consumeNextWith( response -> {
                            Assertions.assertEquals("Karan Public School", response.getSchoolName());
                            Assertions.assertEquals(3, response.getSchoolID());
                        }
                ).verifyComplete();

    }


    @Test
    void getClaassById(){
        Claass claass = new Claass();
        claass.setClassName("4B");
        claass.setClassId(3);
        Mono<Claass>  schoolMono = Mono.just(claass);
        StepVerifier.create(schoolMono)
                .consumeNextWith( response -> {
                            Assertions.assertEquals("4B", response.getClassName());
                            Assertions.assertEquals(3, response.getClassId());
                        }
                ).verifyComplete();

    }



}
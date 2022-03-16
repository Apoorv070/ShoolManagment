package com.Service.School.handler;

import com.Service.School.model.Claass;
import com.Service.School.model.School;
import com.Service.School.model.UserWithSchool;
import com.Service.School.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static java.lang.Integer.parseInt;
import static org.springframework.web.reactive.function.server.RequestPredicates.contentType;

@Component
public class SchoolHandler {

    @Autowired
    SchoolService schoolService;

    // Returning the list of all the schools
    public Mono<ServerResponse> getSchoolList(ServerRequest serverRequest) {
        Flux<School> schoolList = schoolService.getSchoolList();
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(schoolList, School.class);
    }

    // Returning school by ID
    public Mono<ServerResponse> getSchoolById(ServerRequest serverRequest){
        int schoolId = parseInt(serverRequest.pathVariable("schoolId"));
        Mono<School> schoolById = schoolService.getSchoolByID(schoolId);
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(schoolById, School.class);
    }

    // Adding a new School
    public Mono<ServerResponse> addSchool(ServerRequest serverRequest){
        Mono<School> schoolTOAdd = serverRequest.bodyToMono(School.class);
        return schoolTOAdd.flatMap(result ->
                ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(schoolService.addSchool(result),School.class));
    }

    // Adding a class in a existing school
    public Mono<ServerResponse> addClaass(ServerRequest serverRequest){
        Mono<Claass> claassTOAdd = serverRequest.bodyToMono(Claass.class);
        int schoolId = parseInt(serverRequest.pathVariable("schoolId"));
        return claassTOAdd.flatMap(result ->
                ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(schoolService.addClaass(schoolId,result),School.class));
    }

    // Deleting a school
    public Mono<ServerResponse> deleteSchool(ServerRequest serverRequest){
        int SchoolId = Integer.parseInt(serverRequest.pathVariable("schoolId"));
        Mono<Void> schoolDeleted = schoolService.deleteSchool(SchoolId);
        return ServerResponse.ok().body(schoolDeleted,School.class);
    }

    // Get a class by Id
    public Mono<ServerResponse> getClaassById(ServerRequest serverRequest){
        int schoolId = Integer.parseInt(serverRequest.pathVariable("schoolId"));
        int claassId = Integer.parseInt(serverRequest.pathVariable("classId"));
        Mono<Claass> claassById = schoolService.getClaassById(schoolId,claassId);
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(claassById, Claass.class);
    }

    // return school details with all the teachers and student details along side
    public Mono<ServerResponse> getSchoolTeacherStudentDetails(ServerRequest serverRequest){
        Mono<UserWithSchool> userWithSchoolObtained = schoolService.getSchoolTeacherStudentDetails();
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(userWithSchoolObtained,UserWithSchool.class);
    }


}

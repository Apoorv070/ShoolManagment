package com.Service.User.handler;

import com.Service.User.model.School;
import com.Service.User.model.User;
import com.Service.User.model.UserWithClaas;
import com.Service.User.model.UserWithSchool;
import com.Service.User.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static java.lang.Integer.parseInt;

@Component
public class UserHandler {

    @Autowired
    UserService userService;

    // Returning the list of all the users
    public Mono<ServerResponse> getUserList(ServerRequest serverRequest) {
        Flux<User> userList = userService.getUserList();
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(userList, User.class);
    }

    // Returning a user by ID
    public Mono<ServerResponse> getUserById(ServerRequest serverRequest){
        int userId = parseInt(serverRequest.pathVariable("userId"));
        Mono<User> userById = userService.getUserByID(userId);
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(userById, User.class);
    }

    // Adding a new user
    public Mono<ServerResponse> addUser(ServerRequest serverRequest){
        Mono<User> userTOAdd = serverRequest.bodyToMono(User.class);
        return userTOAdd.flatMap(result ->
                ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(userService.addUser(result),User.class));
    }

    // Returning the list of all the schools from the service 1
    public Mono<ServerResponse> getSchoolList(ServerRequest serverRequest){
        Flux<School> schoolList = userService.getSchoolList();
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(schoolList, School.class);
    }

    // Returning the list of all the teachers in a particular school
    public Mono<ServerResponse> teacherDetails(ServerRequest serverRequest){
        int schoolId = parseInt(serverRequest.pathVariable("schoolId"));
        Mono<UserWithSchool> teacherDetails = userService.teacherDetails(schoolId);
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(teacherDetails,User.class);
    }

    // Returning the list of all the students in a particular school
    public Mono<ServerResponse> studentDetails(ServerRequest serverRequest){
        int schoolId = parseInt(serverRequest.pathVariable("schoolId"));
        Mono<UserWithSchool> studentDetails = userService.studentDetails(schoolId);
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(studentDetails,UserWithSchool.class);
    }

    // Deleting a user
    public Mono<ServerResponse> deleteUser(ServerRequest serverRequest){
        int UserId = Integer.parseInt(serverRequest.pathVariable("UserId"));
        Mono<Void> userDeleted  = userService.deleteUser(UserId);
        return ServerResponse.ok().body(userDeleted,User.class);
    }

    // Returning the details of all the users and schools
    public Mono<ServerResponse> userAndSchoolDetails(ServerRequest serverRequest){
        Mono<List<UserWithClaas>> userWithClassObtained = userService.userAndSchoolDetails();
        return ServerResponse.ok().body(userWithClassObtained,UserWithClaas.class);
    }
}

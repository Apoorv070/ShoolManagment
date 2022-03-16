package com.Service.User.service;

import com.Service.User.exception.UserNotfound;
import com.Service.User.model.*;
import com.Service.User.repository.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    UserData userData;

    // Fetching details of school list from service 1
    public Flux<School> getSchoolList(){
        WebClient webClient = WebClient.builder().baseUrl("http://localhost:8081").build();;
        return webClient.get().uri("/allschools").retrieve().bodyToFlux(School.class);
    }

    // Fetching a school by id
    public Mono<School> getSchoolById(int schoolId){
        WebClient webClient = WebClient.builder().baseUrl("http://localhost:8081").build();
        final String link = "/school/"+schoolId;
        return webClient.get().uri(link).retrieve().bodyToMono(School.class);
    }

    // get list of users
    public Flux<User> getUserList() {
        Flux<User> userlList = userData.findAll();
        return userlList;
    }

    // fetch user by a ID
    public Mono<User> getUserByID(int userId){
        Mono<User> userByID = userData.findById(userId).switchIfEmpty(Mono.error(new UserNotfound("User not found with this id"+userId)));
        return userByID;
    }

    // Get class by Id in a school from service 1
    public Mono<Claass> getClassByID(int schoolId,int classId){
        WebClient webClient = WebClient.builder().baseUrl("http://localhost:8081").build();
        final String link = "/school/"+schoolId+"/class/"+classId;
        return webClient.get().uri(link).retrieve().bodyToMono(Claass.class);
    }

    // Add a new user
    public Mono<User> addUser(User userToAdd){
        return userData.save(userToAdd);
    }


    // user details
    public Mono<List<UserWithClaas>> userAndSchoolDetails(){
        Mono<List<User>> usersList = getUserList().collectList();
        return usersList.map(x -> {
            List<UserWithClaas> userWithClaas = new ArrayList<>();
            for(int i =0 ; i < x.size() ; i++){
                UserWithClaas userWithClaas1 = new UserWithClaas();
                userWithClaas1.setUserDetails(x.get(i));

                Mono<Claass> userClass = getClassByID(x.get(i).getSchoolCode(),x.get(i).getClaassCode());
                Mono<School> userSchool =  getSchoolById(x.get(i).getSchoolCode());
                userWithClaas1.setUserSchoolDetails(userSchool.block().getSchoolName());
                userWithClaas1.setUserClassDetails(userClass.block());

                userWithClaas1.setUserDetails(x.get(i));
                userWithClaas.add(userWithClaas1);
            }
            return userWithClaas;
        });

    }

    // to fetch teacher details of a particular school
    public Mono<List<User>> teacherDetailsList(int schoolId){

        Mono<List<User>> allUsers = getUserList().collectList();
        return allUsers.map(x -> {
            List<User> teacherDetails = new ArrayList<>();
            for(int i = 0 ; i < x.size() ; i++){
                if(x.get(i).getSchoolCode()==schoolId && x.get(i).getRole().equals("INSTRUCTOR")){
                    teacherDetails.add(x.get(i));
                }
            }
            return teacherDetails;
        });
    }

    // to fetch teacher details of a particular school along with school details
    public Mono<UserWithSchool>  teacherDetails(int schoolId){
        UserWithSchool userWithSchool = new UserWithSchool();
        teacherDetailsList(schoolId).subscribe(y -> userWithSchool.setUser(y));
        return getSchoolById(schoolId).map(x -> {
            userWithSchool.setSchool(x);
            return userWithSchool;
        });
    }

    // to fetch student details of a particular school
    public Mono<List<User>> studentDetailsList(int schoolId){
        Mono<List<User>> allUsers = getUserList().collectList();
        return allUsers.map(x -> {
            List<User> studentDetails = new ArrayList<>();
            for(int i = 0 ; i < x.size() ; i++){
                if(x.get(i).getSchoolCode()==schoolId && x.get(i).getRole().equals("STUDENT") ){
                    studentDetails.add(x.get(i));
                }
            }
            return studentDetails;
        });
    };

    //  to fetch student details of a particular school along with school details
    public Mono<UserWithSchool> studentDetails(int schoolId){
        UserWithSchool userWithSchool = new UserWithSchool();
        studentDetailsList(schoolId).subscribe(y -> userWithSchool.setUser(y));
        return getSchoolById(schoolId).map(x -> {
            userWithSchool.setSchool(x);
            return userWithSchool;
        });
    }

    // Deleting a User
    public Mono<Void> deleteUser(int UserId) {
        return userData.deleteById(UserId);
    }

}

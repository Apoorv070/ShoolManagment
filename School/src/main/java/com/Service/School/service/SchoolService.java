package com.Service.School.service;

import com.Service.School.exception.SchoolNotfound;
import com.Service.School.model.Claass;
import com.Service.School.model.School;
import com.Service.School.model.User;
import com.Service.School.model.UserWithSchool;
import com.Service.School.repository.SchoolData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.NullLiteral;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SchoolService {

    @Autowired
    SchoolData schoolData;

    // get users list which includes both instructors and students from service 2
    public Flux<User> getUsersList(){
        WebClient webClient = WebClient.builder().baseUrl("http://localhost:8082").build();
        return webClient.get().uri("/allusers").retrieve().bodyToFlux(User.class);
    }

    // Get list of all the schools
    public Flux<School> getSchoolList() {
        Flux<School> schoolList = schoolData.findAll();
        return schoolList;
    }

    // Get the list of all the schools along with teacher details and student details
    public Mono<UserWithSchool> getSchoolTeacherStudentDetails(){

        // List of all the schools
        Mono<List<School>> schoolList = getSchoolList().collectList();
        // List of all the users
        Mono<List<User>> userList = getUsersList().collectList();

        // POJO Class to store both the list of schools and teacher and student details
        UserWithSchool userWithSchool = new UserWithSchool();

        // Adding school in POJO class
        schoolList.doOnNext(x -> {
            userWithSchool.setSchool(x);
        }).subscribe();

        // Adding instructor and student in the pojo class
        return userList.map(x -> {
            List<User> userInstructor = x.stream().filter(a -> a.getRole().equals("INSTRUCTOR")).collect(Collectors.toList());
            List<User> userStudent = x.stream().filter(a -> a.getRole().equals("STUDENT")).collect(Collectors.toList());
            userWithSchool.setTeacher(userInstructor);
            userWithSchool.setStudent(userStudent);
            return userWithSchool;
        });

    }

    // Add class in a particular school
    public Mono<School> addClaass(int schoolId,Claass claassToAdd){
        getSchoolByID(schoolId).subscribe(x -> {
            x.addNewClaass(claassToAdd);
        });
        return getSchoolByID(schoolId);
    }

    // Get school details by school id
    public Mono<School> getSchoolByID(int schoolId){
        Mono<School> schoolByID = schoolData.findById(schoolId).switchIfEmpty(Mono.error(new SchoolNotfound("There is no school with id: "+schoolId)));
        return schoolByID;
    }

    // Adding a school
    public Mono<School> addSchool(School schoolToAdd){
        return schoolData.save(schoolToAdd);
    }

    // Adding a claass in school
    public Mono<School> addClaass(Claass claassToAdd,int schoolId){
        return  getSchoolByID(schoolId).map(schoolInWhichADD -> {
            schoolInWhichADD.getClassDetails().add(claassToAdd);
            return schoolInWhichADD;
        });
    }

    // Deleting a School
    public Mono<Void> deleteSchool(int schoolId){
        return schoolData.deleteById(schoolId);
    }

    // Get class details of a particular school
    public Mono<Claass> getClaassById(int schoolId,int classId){
        Mono<School> schoolObtained = getSchoolByID(schoolId);
        return schoolObtained.map(x -> {
            Claass obj = new Claass();
            for(int i = 0 ; i < x.getClassDetails().size() ; i++){
                if(x.getClassDetails().get(i).getClassId()==classId){
                    obj = x.getClassDetails().get(i);
                }
            }
            return obj;
        });

    }

}

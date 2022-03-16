package com.Service.School.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

// class to stroe the school along with its teacher and student details
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserWithSchool {
    List<User> teacher = new ArrayList<>();
    List<User> student = new ArrayList<>();
    List<School> school = new ArrayList<>();

}

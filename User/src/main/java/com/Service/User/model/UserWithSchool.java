package com.Service.User.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

// combined list for school and its user details here user can be instructor or student
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserWithSchool {
    List<User> user = new ArrayList<>();
    School school;
}

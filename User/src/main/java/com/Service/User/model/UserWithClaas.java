package com.Service.User.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Combined result of user and its school and class details
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserWithClaas {
    User userDetails;
    String userSchoolDetails;
    Claass userClassDetails;
}

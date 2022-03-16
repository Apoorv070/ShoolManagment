package com.Service.User.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    Integer userID;
    String name;
    String role;
    Integer claassCode;
    Integer schoolCode;

}

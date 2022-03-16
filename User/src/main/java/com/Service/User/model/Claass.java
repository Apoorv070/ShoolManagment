package com.Service.User.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Claass {
    Integer classId;
    String className;
    Integer studentStrength;
    Integer instructorCode;

}

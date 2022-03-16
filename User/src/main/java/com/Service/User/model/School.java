package com.Service.User.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "Users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class School {
    String schoolName;
    Integer schoolID;
    List<Claass> classDetails;

}

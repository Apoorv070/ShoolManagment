package com.Service.School.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "Schools")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class School {

    @Id
    Integer schoolID;
    String schoolName;
    List<Claass> classDetails;

    // Function to add a new class in an existing school
    public void addNewClaass(Claass claassToAdd){
        this.classDetails.add(claassToAdd);
    }

}

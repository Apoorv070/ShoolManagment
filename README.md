# ShoolManagment
![image](https://user-images.githubusercontent.com/47479228/158618595-3c4095e3-1459-4547-ba1d-ea8f8a6e70ac.png)


POSTMAN ROUTES

PORT 8081 <br>

// Route to return the list of all the schools
localhost:8081/allschools

// Route to return a particular school by school id
localhost:8081/school/{schoolId}

// Route to return all the schools along with all the users and teachers
localhost:8081/schools

// Route to return a particular class inside a particular school
localhost:8081/school/{schoolId}/school/{schoolId}/class/{classId}

// Route to add a new school
localhost:8081/school/add

// Route to delete a particular school by id
localhost:8081/school/delete/{schoolId}

// Route to add a new class in an existing school
localhost:8081/school/class/{schoolId}


PORT 8082
// Route to show the list of all the users
localhost:8082/allusers

// Route to get a user by a particular ID
localhost:8082/user/{userId}

// Route to add a user
localhost:8082/user/add

// Route to display the user details along with its school and class details
localhost:8082/users

// Route to display teacher details of a particular school along with school details
localhost:8082/user/{schoolId}/teachers

// Route to display student details of a particular school along with school details
localhost:8082/user/{schoolId}/students

// Route to delete a particular user
localhost:8082/user/delete/{UserId}

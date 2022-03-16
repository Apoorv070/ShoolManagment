package com.Service.User.router;


import com.Service.User.handler.UserHandler;
import com.Service.User.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springdoc.webflux.core.fn.SpringdocRouteBuilder.route;


@EnableWebFlux
@Configuration
public class UserRouter implements WebFluxConfigurer {

    @Bean
    public RouterFunction<ServerResponse> routerUser(UserHandler userHandler) {

        // port 8082
        return route().GET("/allusers",// Route to show the list of all the users
                accept(MediaType.APPLICATION_JSON),
                userHandler::getUserList,
                ops -> ops.beanClass(UserService.class).beanMethod("getUserList")).
                build()
                .and(route().GET("/user/{userId}",// Route to get a user by a particular ID
                        accept(MediaType.APPLICATION_JSON),
                        userHandler::getUserById,
                        ops -> ops.beanClass(UserService.class).beanMethod("getUserById")).
                        build()
                        .and(route().POST("/user/add",// Route to add a User
                                accept(MediaType.APPLICATION_JSON),
                                        userHandler::addUser,
                                        ops ->ops.beanClass(UserService.class).beanMethod("addUser")).
                                        build()
                                .and(route().GET("/schools",// Route to get list of all the schools from service 1 (Use Port 8081 while using this route)
                                        accept(MediaType.APPLICATION_JSON),
                                        userHandler::getSchoolList,
                                        ops->ops.beanClass(UserService.class).beanMethod("getSchoolList")).
                                        build()
                                        .and(route().GET("/user/{schoolId}/teachers",// Route to display teacher details of a particular school along with school details
                                                accept(MediaType.APPLICATION_JSON),
                                                userHandler::teacherDetails,
                                                ops -> ops.beanClass(UserService.class).beanMethod("teacherDetails")).
                                                build())
                                        .and(route().DELETE("/user/delete/{UserId}",// Route to delete a particular user
                                                accept(MediaType.APPLICATION_JSON),
                                                userHandler::deleteUser,
                                                ops->ops.beanClass(UserService.class).beanMethod("deleteUser"))
                                                .build()
                                                .and(route().GET("/users",// Route to display the user details along with its school and class details
                                                        accept(MediaType.APPLICATION_JSON),
                                                        userHandler::userAndSchoolDetails,
                                                        ops -> ops.beanClass(UserService.class).beanMethod("userAndSchoolDetails"))
                                                        .build()
                                                        .and(route().GET("/user/{schoolId}/students",// Route to display student details of a particular school along with school details
                                                                accept(MediaType.APPLICATION_JSON),
                                                                userHandler::studentDetails,
                                                                ops -> ops.beanClass(UserService.class).beanMethod("studentDetails"))
                                                                .build()))))));
    }
}

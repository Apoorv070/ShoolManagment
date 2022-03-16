package com.Service.School.routers;


import com.Service.School.handler.SchoolHandler;
import com.Service.School.service.SchoolService;
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
public class SchoolRouter implements WebFluxConfigurer {

    @Bean
    public RouterFunction<ServerResponse> routerSchool(SchoolHandler schoolHandler) {
        // port 8081
        return route().GET("/allschools", // Route to return the list of all the schools
                        accept(MediaType.APPLICATION_JSON),
                        schoolHandler::getSchoolList,
                        ops -> ops.beanClass(SchoolService.class).beanMethod("getSchoolList")).
                build()
                .and(route().GET("/school/{schoolId}" ,// Route to return a particular school by school id
                                accept(MediaType.APPLICATION_JSON),
                                schoolHandler::getSchoolById,
                                ops-> ops.beanClass(SchoolService.class)
                                .beanMethod("getSchoolById"))
                        .build()
                        .and(route().GET("/school/{schoolId}/class/{classId}",// Route to return a particular class inside a particular school
                                accept(MediaType.APPLICATION_JSON),
                                schoolHandler::getClaassById,
                                ops->ops.beanClass(SchoolService.class)
                                        .beanMethod("getClaassById"))
                                .build()
                                .and(route().POST("/school/add",// Route to add a new school
                                        accept(MediaType.APPLICATION_JSON),
                                        schoolHandler::addSchool,
                                        ops-> ops.beanClass(SchoolService.class)
                                                .beanMethod("addSchool"))
                                        .build()
                                        .and(route().DELETE("/school/delete/{schoolId}",// Route to delete a particular school by id
                                                accept(MediaType.APPLICATION_JSON),
                                                schoolHandler::deleteSchool,
                                                ops->ops.beanClass(SchoolService.class)
                                                        .beanMethod("deleteSchool"))
                                                .build()
                                                .and(route().POST("/school/class/{schoolId}",// Route to add a new class in an existing school
                                                        accept(MediaType.APPLICATION_JSON),
                                                        schoolHandler::addClaass,
                                                        ops->ops.beanClass(SchoolService.class)
                                                                .beanMethod("addclaass"))
                                                        .build()
                                                        .and(route().GET("/schools", // Route to return all the schools along with all the users and teachers
                                                                accept(MediaType.APPLICATION_JSON),
                                                                schoolHandler::getSchoolTeacherStudentDetails,
                                                                ops -> ops.beanClass(SchoolService.class)
                                                                        .beanMethod("getSchoolTeacherStudentDetails"))
                                                                .build()))))));
    }
}


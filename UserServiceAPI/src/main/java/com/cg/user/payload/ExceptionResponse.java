package com.cg.user.payload;

import org.springframework.http.HttpStatus;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExceptionResponse {
    private String msg;
    private boolean success;
    private HttpStatus status;

}

//gatling
//monolithic vs microservices
//cloud concepts paas iaas, saas
//pcf ocp
//annotations
package com.cg.user.vo;

import com.cg.user.payload.CustomResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseVO {
    public static ResponseEntity<Object> userResponseBuilder(String msg, boolean successStatus, HttpStatus status, Object obj){
        CustomResponse response = CustomResponse.builder().msg(msg).success(successStatus).status(status).responseObject(obj).build();
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }
    public static ResponseEntity<Object> jaxbResponseBuilder(String msg, boolean successStatus, HttpStatus status){
        CustomResponse response = CustomResponse.builder().msg(msg).success(successStatus).status(status).build();
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }
}

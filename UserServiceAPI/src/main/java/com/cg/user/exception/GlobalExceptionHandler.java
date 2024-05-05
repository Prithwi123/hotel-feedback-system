package com.cg.user.exception;

import com.cg.user.payload.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//import java.util.HashMap;
//import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handlerResourceNotFoundException(ResourceNotFoundException ex){

        //old technique
		/*
		Map map = new HashMap();
		map.put("message", ex.getMessage());
		map.put("success", false);
		map.put("status", HttpStatus.NOT_FOUND)
		*/


        String message = ex.getMessage();
        ExceptionResponse response = ExceptionResponse.builder().msg(message).success(true).status(HttpStatus.NOT_FOUND).build();
        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.NOT_FOUND);

    }
}

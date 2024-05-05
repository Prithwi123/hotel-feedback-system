package com.cg.user.payload;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomResponse {
    private String msg;
    private boolean success;
    private HttpStatus status;
    private Object responseObject;
}

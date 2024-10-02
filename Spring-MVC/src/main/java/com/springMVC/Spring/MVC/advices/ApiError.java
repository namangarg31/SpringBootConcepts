package com.springMVC.Spring.MVC.advices;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Builder
@Data
public class ApiError {
    private HttpStatus httpStatus;
    private String error;
    private List<String> subErrors;
}

package com.mohit.blog.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mohit.blog.payloads.ApiResponse;

@RestControllerAdvice //whenever any controller will throw exception it will work
public class GlobalExceptionHandler {
   @ExceptionHandler(ResourceNotFoundException.class) //Whenever ResorceNotFoundExcption.class exception it will work
   public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex){//we will recieve object of resourenotfoundexception
	String message = ex.getMessage();
	ApiResponse apiResponse = new ApiResponse(message,false);
	return new ResponseEntity<>(apiResponse,HttpStatus.NOT_FOUND);
	   
   }
}

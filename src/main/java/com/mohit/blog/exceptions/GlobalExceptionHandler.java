package com.mohit.blog.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
   @ExceptionHandler(MethodArgumentNotValidException.class)
   public ResponseEntity<Map<String,String>> handleMethodArgsNotValidExpection(MethodArgumentNotValidException ex){
	   Map<String,String> resp = new HashMap<>();
	   ex.getBindingResult().getAllErrors().forEach((error)->{
		   String fieldName = ((FieldError)error).getField();
		   String message = error.getDefaultMessage();
		   resp.put(fieldName,message);
	   });
	   return new ResponseEntity<Map<String,String>>(resp,HttpStatus.BAD_REQUEST);
   }
}

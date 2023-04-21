package com.mohit.blog.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException{
	String resourceName;
	String fieldName;
	long fieldValue;
	public ResourceNotFoundException(String resoureName,String fieldName,long fieldValue) {
		super(String.format("%s not found with %s:%l",resoureName,fieldName,fieldValue));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
		
		// TODO Auto-generated constructor stub
	}
	

}

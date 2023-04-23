package com.mohit.blog.payloads;

import java.util.Date;

import com.mohit.blog.entities.Category;
import com.mohit.blog.entities.User;

import lombok.Data;
@Data
public class PostDto {

	private String title;
	
	private String content;
	
	//private String imageName="default.png";
     
	private Date addedDate;
	private CategoryDto category;
	
	private UserDto user;
}

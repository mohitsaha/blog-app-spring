package com.mohit.blog.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="post")
@Data
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer postId;
	
    @Column(name = "post_title",length = 100,nullable = false)
	private String title;
	
    @Column(length = 10000)
	private String content;
	
	private String imageName;
	
	private Date addedDate;
	
	@ManyToOne
	private Category category;
	
	@ManyToOne
	private User user;
	
	
}

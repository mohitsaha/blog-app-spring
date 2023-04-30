package com.mohit.blog.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mohit.blog.payloads.ApiResponse;
import com.mohit.blog.payloads.CommentDto;
import com.mohit.blog.services.CommentService;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    @Autowired
	private CommentService commentService;
	
    @Autowired
    private ModelMapper modelMapper;
    
	@PostMapping("/post/{postId}/comments")
	public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto,@PathVariable Integer postId){
		CommentDto createdComment = this.commentService.createComment(commentDto, postId);
		return new ResponseEntity<>(createdComment,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/comments/{commentId}")
	public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId){
		this.commentService.deleteComment(commentId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("resource deleted",true),HttpStatus.OK);
	}
	
	
}

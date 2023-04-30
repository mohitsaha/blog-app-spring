package com.mohit.blog.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.mohit.blog.entities.Comment;
import com.mohit.blog.entities.Post;
import com.mohit.blog.exceptions.ResourceNotFoundException;
import com.mohit.blog.payloads.CommentDto;
import com.mohit.blog.repositories.CommentRepo;
import com.mohit.blog.repositories.PostRepo;
import com.mohit.blog.services.CommentService;

public class CommentServiceImpl implements CommentService {
    @Autowired
	private PostRepo postRepo;
    @Autowired
	private CommentRepo commentRepo;
	
    @Autowired
    private ModelMapper modelMapper;
    
	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {
		Post  post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","Id",postId));
		Comment comment = this.modelMapper.map(commentDto, Comment.class);
		comment.setPost(post);
		Comment savedComment = this.commentRepo.save(comment);
		return this.modelMapper.map(savedComment, CommentDto.class);
	}

	@Override
	public void deleteComment(Integer commentId) {
        Comment com = this.commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("comment","id",commentId));	
        this.commentRepo.delete(com);
	}

}

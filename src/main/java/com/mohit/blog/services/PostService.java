package com.mohit.blog.services;

import java.util.List;

import com.mohit.blog.entities.Post;
import com.mohit.blog.payloads.PostDto;

public interface PostService {
	
	PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);

	PostDto updatePost(PostDto postDto,Integer postId);
	
	void deletePost(Integer postId);
	
	List<PostDto> getAllPost();
	
	PostDto getPostById(Integer postId);
	
	List<PostDto> getPostsByCategory(Integer categoryId);
	
	List<PostDto> getPostByUser(Integer userId);
	
	List<PostDto> seatchPost(String keyword);
	
}

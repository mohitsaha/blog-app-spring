package com.mohit.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mohit.blog.payloads.ApiResponse;
import com.mohit.blog.payloads.PostDto;
import com.mohit.blog.payloads.PostResponse;
import com.mohit.blog.services.PostService;

@RestController
@RequestMapping("/api/posts")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@PostMapping("/user/{userId}/category/{categoryId}/Posts")
	public ResponseEntity<PostDto> createPost(
		@RequestBody PostDto postDto,
		@PathVariable Integer userId,
		@PathVariable Integer categoryId
		)
	{
		PostDto createPost = this.postService.createPost(postDto, userId, categoryId);
		return new ResponseEntity<PostDto>(createPost,HttpStatus.CREATED);
	}
	
    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId){
    	List<PostDto> posts = this.postService.getPostByUser(userId);
    	return new ResponseEntity<>(posts,HttpStatus.OK);
    }
    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer categoryId){
    	List<PostDto> posts = this.postService.getPostsByCategory(categoryId);
    	return new ResponseEntity<>(posts,HttpStatus.OK);
    }
    
    @GetMapping("/posts")
    public ResponseEntity<PostResponse> getAllPost(
    		@RequestParam(value="pageNumber",defaultValue="0",required =false) Integer pageNumber,
    		@RequestParam(value="pageSize",defaultValue="5",required =false) Integer pageSize,
    		@RequestParam(value="sortBy",defaultValue="postId",required=false) String sortBy,
    		@RequestParam(value="sortDir",defaultValue="asc",required=false) String sortDir
    		)
    {
    	//List<PostDto> allPost = this.postService.getAllPost(pageNumber,pageSize);
    	PostResponse ps = this.postService.getAllPost(pageNumber,pageSize,sortBy,sortDir);
    	return new ResponseEntity<PostResponse>(ps,HttpStatus.OK);
    }
    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId)
    {
    	PostDto postDto = this.postService.getPostById(postId);
    	return new ResponseEntity<PostDto>(postDto,HttpStatus.OK);
    }
    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable Integer postId){
    	PostDto updatePost = this.postService.updatePost(postDto, postId);
    	return new ResponseEntity<>(updatePost,HttpStatus.OK);
    }
    
    @DeleteMapping("/posts/{postId}")
    public ApiResponse deletePost(@PathVariable Integer postId) {
    	this.postService.deletePost(postId);
    	return new ApiResponse("post is deleted",true);
    }
    @GetMapping("/posts/search/{keyword}")
    public ResponseEntity<List<PostDto>> searchPosts(
    		@PathVariable("keywords") String keyword
    		){
    	List<PostDto> result = this.postService.searchPost(keyword);
    	return new ResponseEntity<>(result,HttpStatus.OK);
    }
}

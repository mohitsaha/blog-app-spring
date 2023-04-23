package com.mohit.blog.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mohit.blog.entities.Category;
import com.mohit.blog.entities.Post;
import com.mohit.blog.entities.User;
import com.mohit.blog.exceptions.ResourceNotFoundException;
import com.mohit.blog.payloads.PostDto;
import com.mohit.blog.payloads.PostResponse;
import com.mohit.blog.repositories.CategoryRepo;
import com.mohit.blog.repositories.PostRepo;
import com.mohit.blog.repositories.UserRepo;
import com.mohit.blog.services.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Override
	public PostDto createPost(PostDto postDto,Integer userId,Integer categoryId){		
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user","id",userId));
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("category","id",categoryId));
		Post post = this.modelMapper.map(postDto, Post.class);
		post.setImageName("dafault.png");
		post.setAddedDate(new Date());
		post.setCategory(category);
		Post newPost = this.postRepo.save(post);
		return this.modelMapper.map(newPost, PostDto.class); 
	 }

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(()->new
        		ResourceNotFoundException("post","id",postId));
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        Post updatedPost=this.postRepo.save(post);
        
		return this.modelMapper.map(updatedPost, PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {
     Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("post","id",postId));
     this.postRepo.delete(post);
	}

	@Override
	public PostResponse getAllPost(Integer pageSize,Integer pageNumber,String sortBy,String sortDir) {
		Sort sort = sortDir.equalsIgnoreCase("asc")? Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
		PageRequest p  = PageRequest.of(pageNumber, pageSize,sort);
		Page<Post> pagePost = this.postRepo.findAll(p);
		List <Post> allPosts = pagePost.getContent();
        List<PostDto> postDtos =allPosts.stream().map(post->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
		PostResponse ps = new PostResponse();
		ps.setContent(postDtos);
		ps.setPageNumber(pagePost.getNumber());
		ps.setPageSize(pagePost.getSize());
		ps.setTotalElements(pagePost.getTotalElements());
		ps.setTotalPages(pagePost.getTotalPages());
		ps.setLastPage(pagePost.isLast());
        return ps;
	}

	@Override
	public PostDto getPostById(Integer postId) {
         Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("post","id",postId));
 		return this.modelMapper.map(post, PostDto.class);

	}

	@Override
	public List<PostDto> getPostsByCategory(Integer categoryId) {
        Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("category","id",categoryId));
		List<Post> posts = this.postRepo.findByCategory(cat);
		List<PostDto> postsDto= posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return postsDto;
	}

	@Override
	public List<PostDto> getPostByUser(Integer userId) {
      User user = this.userRepo.findById(userId)
    		      .orElseThrow(()-> new ResourceNotFoundException("user","userId",userId));
	  List<Post> posts = this.postRepo.findByUser(user);
	  List<PostDto> postDtos = posts.stream().map((post)->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
      return postDtos;
	}

	@Override
	public List<PostDto> searchPost(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

}

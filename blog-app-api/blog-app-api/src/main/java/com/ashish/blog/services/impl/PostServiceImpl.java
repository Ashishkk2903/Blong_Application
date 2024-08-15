package com.ashish.blog.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.hibernate.validator.cfg.context.ReturnValueConstraintMappingContext;
import org.modelmapper.ModelMapper;
import org.modelmapper.internal.bytebuddy.asm.Advice.This;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashish.blog.entity.Category;
import com.ashish.blog.entity.Post;
import com.ashish.blog.entity.user;
import com.ashish.blog.exceptions.ResourceNotFoundException;
import com.ashish.blog.payloads.PostDto;
import com.ashish.blog.repositories.Categoryrepo;
import com.ashish.blog.repositories.PostRepo;
import com.ashish.blog.repositories.userRepo;
import com.ashish.blog.services.PostService;


@Service
public class PostServiceImpl implements PostService{
	@Autowired
	private PostRepo postRepo;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private userRepo userRepo;
	@Autowired
	private Categoryrepo categoryrepo;

	@Override
	public PostDto createPost(PostDto postDto,Integer userid,Integer categoryid)
	{
		user user=this.userRepo.findById(userid).orElseThrow(()-> new ResourceNotFoundException("user", "userid", userid));
		Category category=this.categoryrepo.findById(categoryid).orElseThrow(()-> new ResourceNotFoundException("category", "categoryid", categoryid));
		Post post=this.modelMapper.map(postDto, Post.class);
		post.setImageName("Default.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		Post savedpost=this.postRepo.save(post);
		return this.modelMapper.map(savedpost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		Post post=this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("post", "postid", postId));
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());
		
		Post updatedpost=this.postRepo.save(post);
		return this.modelMapper.map(updatedpost, PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {
		Post post=this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("post", "postid", postId));
		this.postRepo.delete(post);
	}

	@Override
	public PostDto getPost(Integer postId) {
		Post post=this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("post", "postid", postId));
		return this.modelMapper.map(post, PostDto.class);
	}

	@Override
	public List<PostDto> getAllPosts() {
		List<Post> allposts=this.postRepo.findAll();
		List<PostDto> postDtos=allposts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public List<PostDto> getbyCategory(Integer categoryId) {
		Category category=this.categoryrepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("category", "categoryid", categoryId));
		List<Post> posts=this.postRepo.findByCategory(category);
		List<PostDto>postdtolist=posts.stream().map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postdtolist;
	}

	@Override
	public List<PostDto> getbyuser(Integer userId) {
		user user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user", "userid", userId));
		List<Post>posts=this.postRepo.findByuser(user);
		List<PostDto> postdto=posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postdto;
	}
 
	@Override
	public List<PostDto> searchPosts(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

}

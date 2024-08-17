package com.ashish.blog.services.impl;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.hibernate.validator.cfg.context.ReturnValueConstraintMappingContext;
import org.modelmapper.ModelMapper;
import org.modelmapper.internal.bytebuddy.asm.Advice.This;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ashish.blog.entity.Category;
import com.ashish.blog.entity.Post;
import com.ashish.blog.entity.user;
import com.ashish.blog.exceptions.ResourceNotFoundException;
import com.ashish.blog.payloads.PostDto;
import com.ashish.blog.payloads.PostResponce;
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
	public PostResponce getAllPosts(Integer pageNumber,Integer pageSize,String sortby,String sortdir) 
	{
		Sort sort=null;
		if(sortdir.equalsIgnoreCase("asc"))
			sort=Sort.by(sortby).ascending();
		else 
			sort=Sort.by(sortby).descending();
		
		Pageable p=PageRequest.of(pageNumber, pageSize,sort);
		
		Page<Post> pagePost=this.postRepo.findAll(p);
		List<Post> allposts=pagePost.getContent();	 	
		
		List<PostDto> postDtos=allposts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		PostResponce postResponce=new PostResponce();
		postResponce.setContent(postDtos);
		postResponce.setPageNumber(pagePost.getNumber());
		postResponce.setPageSize(pagePost.getSize());
		postResponce.setTotalElements(pagePost.getTotalElements());
		postResponce.setTotalPages(pagePost.getTotalPages());
		postResponce.setLastpage(pagePost.isLast());
		return postResponce;
	}

	@Override
	public PostResponce getbyCategory(Integer categoryId,Integer pageNumber,Integer pageSize) {
		Pageable p=PageRequest.of(pageNumber, pageSize);
		Category category=this.categoryrepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("category", "categoryid", categoryId));
		Page<Post> posts=this.postRepo.findByCategory(category,p);
		List<PostDto>postdtolist=posts.stream().map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		PostResponce postResponce=new PostResponce();
		postResponce.setContent(postdtolist);
		postResponce.setPageNumber(posts.getNumber());
		postResponce.setPageSize(posts.getSize());
		postResponce.setTotalElements(posts.getTotalElements());
		postResponce.setTotalPages(posts.getTotalPages());
		postResponce.setLastpage(posts.isLast());
		return postResponce;
	}

	@Override
	public PostResponce getbyuser(Integer userId,Integer pageNumber,Integer pageSize) {
		Pageable p=PageRequest.of(pageNumber, pageSize);
		user user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user", "userid", userId));
		Page<Post>posts=this.postRepo.findByuser(user,p);
		List<PostDto> postdto=posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		PostResponce postResponce=new PostResponce();
		postResponce.setContent(postdto);
		postResponce.setPageNumber(posts.getNumber());
		postResponce.setPageSize(posts.getSize());
		postResponce.setTotalElements(posts.getTotalElements());
		postResponce.setTotalPages(posts.getTotalPages());
		postResponce.setLastpage(posts.isLast());
		return postResponce;
	}
	
	
 
	@Override
	public List<PostDto> searchPosts(String keyword) 
	{
		List<Post> posts=this.postRepo.searchByTitle(keyword);
		List<PostDto> postDtos=posts.stream().map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		return postDtos;
	}

}

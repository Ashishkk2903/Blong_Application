package com.ashish.blog.controllers;

import java.nio.channels.NonReadableChannelException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ashish.blog.entity.Post;
import com.ashish.blog.payloads.PostDto;
import com.ashish.blog.payloads.apiresponce;
import com.ashish.blog.services.PostService;

import jakarta.persistence.PostPersist;

@RestController
@RequestMapping("api/")
public class PostContoller {
	
	@Autowired
	private PostService postService;
	
	@PostMapping("/user/{userid}/category/{categoryid}/posts")
	public ResponseEntity<PostDto>createpost(
			@RequestBody PostDto postDto,
			@PathVariable Integer userid,
			@PathVariable Integer categoryid)
	{
		PostDto createpost=this.postService.createPost(postDto, userid, categoryid);
		return new ResponseEntity<PostDto>(createpost,HttpStatus.CREATED);
	}
	
	@GetMapping("/user/{userid}/posts")
	public ResponseEntity<List<PostDto>> getpostbyuser(@PathVariable Integer userid)
	{
		List<PostDto> postDto=this.postService.getbyuser(userid);
		return new ResponseEntity<List<PostDto>>(postDto,HttpStatus.OK);
	}
	
	@GetMapping("/category/{categoryid}/posts")
	public ResponseEntity<List<PostDto>> getpostbycategory(@PathVariable Integer categoryid)
	{
		List<PostDto> postDto=this.postService.getbyCategory(categoryid);
		return new ResponseEntity<List<PostDto>>(postDto,HttpStatus.OK);
	}
	
	@DeleteMapping("posts/{postid}")
	public ResponseEntity<apiresponce> deletepost(@PathVariable Integer postid)
	{
		this.postService.deletePost(postid);
		return new ResponseEntity<apiresponce>(new apiresponce("post is succesfully deleted",true),HttpStatus.OK);
	}
	
	@PutMapping("posts/{postid}")
	public ResponseEntity<PostDto> updatepost(@RequestBody PostDto postDto,@PathVariable Integer postid)
	{
		PostDto post=this.postService.updatePost(postDto, postid);
		return new ResponseEntity<PostDto>(post,HttpStatus.OK);
	}
	
	@GetMapping("/posts")
	public ResponseEntity<List<PostDto>>getallpost()
	{
		List<PostDto> posts=this.postService.getAllPosts();
		return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
	}
	
	@GetMapping("/posts/{postid}")
	public ResponseEntity<PostDto> getpostbyid(@PathVariable Integer postid)
	{
		PostDto post=this.postService.getPost(postid);
		return new ResponseEntity<PostDto>(post,HttpStatus.OK);
	}


}

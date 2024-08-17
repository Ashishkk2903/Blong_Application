package com.ashish.blog.controllers;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.NonReadableChannelException;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.util.StreamUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
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
import org.springframework.web.multipart.MultipartFile;

import com.ashish.blog.config.AppConstants;
import com.ashish.blog.entity.Post;
import com.ashish.blog.payloads.FileResponce;
import com.ashish.blog.payloads.PostDto;
import com.ashish.blog.payloads.PostResponce;
import com.ashish.blog.payloads.apiresponce;
import com.ashish.blog.services.FileService;
import com.ashish.blog.services.PostService;

import jakarta.persistence.PostPersist;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("api/")
public class PostContoller {
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private FileService fileService;
	
	@Value("${project.image}")
	private String path;
	
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
	public ResponseEntity<PostResponce> getpostbyuser(
			@PathVariable Integer userid,
			@RequestParam(value = "pageNumber",defaultValue = "0",required = false) Integer pagenumber,
			@RequestParam(value = "pageSize",defaultValue = "5",required = false) Integer pagesize)
	{
		PostResponce post=this.postService.getbyuser(userid,pagenumber,pagesize);
		return new ResponseEntity<PostResponce>(post,HttpStatus.OK);
	}
	
	@GetMapping("/category/{categoryid}/posts")
	public ResponseEntity<PostResponce> getpostbycategory(
			@PathVariable Integer categoryid,
			@RequestParam(value = "pageNumber",defaultValue = "0",required = false) Integer pagenumber,
			@RequestParam(value = "pageSize",defaultValue = "5",required = false) Integer pagesize)
	{
		PostResponce post=this.postService.getbyCategory(categoryid,pagenumber,pagesize);
		return new ResponseEntity<PostResponce>(post,HttpStatus.OK);
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
	public ResponseEntity<PostResponce> getallpost(
			@RequestParam(value = "pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false) Integer pagenumber,
			@RequestParam(value = "pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false) Integer pagesize,
			@RequestParam(value="sortBy",defaultValue = AppConstants.SORT_BY,required = false) String sortby,
			@RequestParam(value="sortDir",defaultValue =AppConstants.SORT_DIR,required = false) String sortdir
			)
	{
		PostResponce posts=this.postService.getAllPosts(pagenumber,pagesize,sortby,sortdir);
		return new ResponseEntity<PostResponce>(posts,HttpStatus.OK);
	}
	
	@GetMapping("/posts/{postid}")
	public ResponseEntity<PostDto> getpostbyid(@PathVariable Integer postid)
	{ 	
		PostDto post=this.postService.getPost(postid);
		return new ResponseEntity<PostDto>(post,HttpStatus.OK);
	}
	
	@GetMapping("/posts/search/{keyword}")
	public ResponseEntity<List<PostDto>> searchbytitle(@PathVariable String keyword)
	{
		List<PostDto> result=this.postService.searchPosts(keyword);
		return new ResponseEntity<List<PostDto>>(result,HttpStatus.OK);
	}
	
	@PostMapping("/post/image/upload/{postid}")
	public ResponseEntity<PostDto>uploadpostimage(@RequestParam("image") MultipartFile f,
			@PathVariable Integer postid) throws IOException
	{
		PostDto postdto=this.postService.getPost(postid);
		String filename=this.fileService.uploadImage(path, f);
		postdto.setImageName(filename);
		PostDto updatedpostdto=this.postService.updatePost(postdto, postid);
		return new ResponseEntity<PostDto>(updatedpostdto,HttpStatus.OK);
	}
	
	@GetMapping(value = "/post/image/{imagename}",produces =MediaType.IMAGE_JPEG_VALUE)
	public void downloadimage(@PathVariable String imagename,
			HttpServletResponse responce) throws IOException
	{
		InputStream resource=this.fileService.getresource(path, imagename);
		responce.setContentType(MediaType.IMAGE_JPEG_VALUE);
		org.springframework.util.StreamUtils.copy(resource, responce.getOutputStream());
	}
	
	
	


}

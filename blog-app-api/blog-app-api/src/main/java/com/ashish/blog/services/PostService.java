package com.ashish.blog.services;

import java.util.List;

import com.ashish.blog.entity.Post;
import com.ashish.blog.payloads.PostDto;

public interface PostService {
	
	PostDto createPost(PostDto postDto,Integer userid,Integer categoryid);
	PostDto updatePost(PostDto postDto,Integer postId);
	void deletePost(Integer postId);
	PostDto getPost(Integer postId); 
	List<PostDto> getAllPosts();
	List<PostDto> getbyCategory(Integer categoryId);
	List<PostDto> getbyuser(Integer userId);
	List<PostDto>searchPosts(String keyword);
}

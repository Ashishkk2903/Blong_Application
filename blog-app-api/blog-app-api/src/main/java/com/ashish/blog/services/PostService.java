package com.ashish.blog.services;

import java.util.List;

import com.ashish.blog.entity.Post;
import com.ashish.blog.payloads.PostDto;
import com.ashish.blog.payloads.PostResponce;

public interface PostService {
	
	PostDto createPost(PostDto postDto,Integer userid,Integer categoryid);
	PostDto updatePost(PostDto postDto,Integer postId);
	void deletePost(Integer postId);
	PostDto getPost(Integer postId); 
	PostResponce getAllPosts(Integer pageNumber,Integer pageSize,String sortby,String sortdir);
	
	PostResponce getbyCategory(Integer categoryId,Integer pageNumber,Integer pageSize);
	PostResponce getbyuser(Integer userId,Integer pageNumber,Integer pageSize);
	
	List<PostDto>searchPosts(String keyword);
}

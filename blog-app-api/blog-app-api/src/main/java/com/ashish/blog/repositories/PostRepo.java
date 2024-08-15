package com.ashish.blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashish.blog.entity.Category;
import com.ashish.blog.entity.Post;
import com.ashish.blog.entity.user;

public interface PostRepo extends JpaRepository<Post, Integer> {
	List<Post>findByuser(user user);
	List<Post>findByCategory(Category category);
}
 
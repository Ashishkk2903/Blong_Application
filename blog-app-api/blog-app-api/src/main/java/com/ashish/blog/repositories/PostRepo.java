package com.ashish.blog.repositories;



import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ashish.blog.entity.Category;
import com.ashish.blog.entity.Post;
import com.ashish.blog.entity.user;

public interface PostRepo extends JpaRepository<Post, Integer> {
	Page<Post>findByuser(user user,Pageable p);
	Page<Post>findByCategory(Category category,Pageable p);
	
	@Query("select p from Post p where p.Title Like %:key%")
	List<Post>searchByTitle(@Param("key") String Title);
}
 
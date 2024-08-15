package com.ashish.blog.payloads;

import java.util.Date;

import com.ashish.blog.entity.Category;
import com.ashish.blog.entity.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {
	private int postid;
	private String Title;
	private String content;
	private String imageName;
	private Date addedDate; 
	private CategoryDto category;
	private userDto user;
	
	
}

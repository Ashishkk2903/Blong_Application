package com.ashish.blog.entity;

import java.util.Date;

import org.hibernate.annotations.ManyToAny;
import org.hibernate.bytecode.internal.bytebuddy.PrivateAccessorException;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer postid;
	@Column(nullable = false,length = 100)
	private String Title;
	@Column(length = 10000)
	private String content;
	private String imageName;
	private Date addedDate; 
	
	@ManyToOne
	private Category category;
	
	@ManyToOne
	private user user;

}

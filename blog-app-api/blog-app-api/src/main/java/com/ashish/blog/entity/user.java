package com.ashish.blog.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Setter
@Getter
public class user {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	 private int id;
	 
	 @Column(name = "user_name",length = 100,nullable = false)
	 private String name;
	 private String email;
	 private String password;
	 private String about;

}

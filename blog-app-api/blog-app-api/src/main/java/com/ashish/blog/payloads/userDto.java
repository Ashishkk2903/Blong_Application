package com.ashish.blog.payloads;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class userDto 
{
	private int id;
	@NotEmpty
	@Size(min=4,message = "usename must be minimum of  4 characters !!")
	private String name;
	
	@Email(message = "your email address is not valid !!")
	 private String email;
	
	@NotEmpty
	@Size(min=3,max=10,message = "password length must be between 3-10 !!")
	 private String password;
	
	@NotEmpty
	 private String about;
}

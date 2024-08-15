package com.ashish.blog.controllers;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ashish.blog.payloads.apiresponce;
import com.ashish.blog.payloads.userDto;
import com.ashish.blog.services.userService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/api/users")
public class usercontroller {
	
	@Autowired
	private userService uService;
	
	@PostMapping("/")
	public ResponseEntity<userDto> createuser( @RequestBody @Valid userDto usdto)
	{
		userDto createduserDto=this.uService.createuser(usdto);
		return new ResponseEntity<>(createduserDto,HttpStatus.CREATED);
	}
	
	@PutMapping("/{userid}")
	public ResponseEntity<userDto> updateuser( @RequestBody @Valid userDto userDto,@PathVariable Integer userid)
	{
		userDto updateUserDto=this.uService.updateuser(userDto, userid);
		return ResponseEntity.ok(updateUserDto);
	}
	@DeleteMapping("/{userid}")
	public ResponseEntity<apiresponce> deleteuser(@PathVariable Integer userid)
	{
		this.uService.deleteuser(userid);
		return new ResponseEntity<apiresponce>(new apiresponce("user deleted", false),HttpStatus.OK);
	}
	
	@GetMapping("/{userid}")
	public ResponseEntity<userDto> getuser(@PathVariable Integer userid)
	{
		return  ResponseEntity.ok(this.uService.getuserbyid(userid));
	}
	
	@GetMapping("/")
	public ResponseEntity<List<userDto>> getalluser()
	{
		return ResponseEntity.ok(this.uService.getusers());
	}
	
} 

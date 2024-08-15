package com.ashish.blog.services.impl;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.apache.catalina.User;
import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.ashish.blog.exceptions.*;
import com.ashish.blog.entity.user;
import com.ashish.blog.payloads.userDto;
import com.ashish.blog.repositories.userRepo;
import com.ashish.blog.services.userService;

@Service
public class userserviceimpl implements userService {
	
	@Autowired
	private userRepo userRepo;
	
	@Autowired
	private ModelMapper mapper;

	@Override
	public userDto createuser(userDto userdto) 
	{
		user user1=this.dtotouser(userdto);
		user savedUser=this.userRepo.save(user1);
		
		return this.usertoDto(savedUser);
	}

	@Override
	public userDto updateuser(userDto user, Integer userid) {
		
		user user1=this.userRepo.findById(userid).orElseThrow(()-> new ResourceNotFoundException("user","id",userid));
		user1.setName(user.getName());
		user1.setEmail(user.getEmail());
		user1.setPassword(user.getPassword());
		user1.setAbout(user.getAbout());
		user updatedUser=this.userRepo.save(user1);
		return mapper.map(updatedUser, userDto.class);
	}

	@Override
	public void deleteuser(Integer userid) {
		user user1=this.userRepo.findById(userid).orElseThrow(()-> new ResourceNotFoundException("user", "id", userid));
		this.userRepo.delete(user1);
	}

	@Override
	public userDto getuserbyid(Integer userid) {
		user user2=this.userRepo.findById(userid).orElseThrow(()->new ResourceNotFoundException("user", "id", userid));
		return this.usertoDto(user2);
	}

	@Override
	public List<userDto> getusers() {
		List<user> us=this.userRepo.findAll();
		List<userDto>userDtos=us.stream().map(user ->this.usertoDto(user)).collect(Collectors.toList());
		
		return userDtos;
	}
	
	private user dtotouser(userDto use)
	{
		user us=this.mapper.map(use, user.class);
		return us;
	}
	
	private userDto usertoDto(user use)
	{
		userDto us=mapper.map(use, userDto.class);
		return us;
	}

}

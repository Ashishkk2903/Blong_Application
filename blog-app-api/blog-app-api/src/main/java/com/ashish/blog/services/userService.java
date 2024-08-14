package com.ashish.blog.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ashish.blog.payloads.userDto;
@Service
public interface userService {
	userDto createuser(userDto user);
	userDto updateuser(userDto user,Integer userid);
	void deleteuser(Integer userid);
	userDto getuserbyid(Integer userid);
	List<userDto> getusers();
}

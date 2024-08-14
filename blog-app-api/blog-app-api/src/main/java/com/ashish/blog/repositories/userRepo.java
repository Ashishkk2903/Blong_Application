package com.ashish.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashish.blog.entity.user;

public interface userRepo extends JpaRepository<user, Integer>  {
}

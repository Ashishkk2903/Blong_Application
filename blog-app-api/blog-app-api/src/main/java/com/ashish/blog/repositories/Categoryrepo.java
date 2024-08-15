package com.ashish.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashish.blog.entity.Category;

public interface Categoryrepo extends JpaRepository<Category, Integer> {

}

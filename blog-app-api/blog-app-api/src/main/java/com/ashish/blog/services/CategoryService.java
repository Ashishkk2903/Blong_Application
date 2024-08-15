package com.ashish.blog.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ashish.blog.entity.Category;
import com.ashish.blog.payloads.CategoryDto;

@Service
public interface CategoryService
{
	public CategoryDto createCategory(CategoryDto categoryDto);
	public CategoryDto updateCategory(CategoryDto categoryDto,Integer categoryid);
	public void deleteCategory(Integer categoryid);
	public CategoryDto getCategory(Integer categoryid);
	public List<CategoryDto> getallcategories();
}

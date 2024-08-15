package com.ashish.blog.services.impl;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.apache.catalina.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashish.blog.entity.Category;
import com.ashish.blog.exceptions.ResourceNotFoundException;
import com.ashish.blog.payloads.CategoryDto;
import com.ashish.blog.repositories.Categoryrepo;
import com.ashish.blog.services.CategoryService;

@Service
public class CategoryServiceimpl implements CategoryService {
	
	@Autowired
	private Categoryrepo categoryrepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category cat=modelMapper.map(categoryDto, Category.class);
		Category addedcat=categoryrepo.save(cat);
		return modelMapper.map(addedcat, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryid) {
		Category cat=this.categoryrepo.findById(categoryid).orElseThrow(()-> new ResourceNotFoundException("category","CategoryId", categoryid));
		cat.setCategoryDescription(categoryDto.getCategoryDescription());
		cat.setCategoryTitle(categoryDto.getCategoryTitle());
		
		Category updatedCategory=this.categoryrepo.save(cat);
		return this.modelMapper.map(updatedCategory, CategoryDto.class);
	}

	@Override
	public void deleteCategory(Integer categoryid) {
		Category category=this.categoryrepo.findById(categoryid).orElseThrow(()-> new ResourceNotFoundException("category", "CategoryId", categoryid));
		categoryrepo.delete(category);
	}

	@Override
	public CategoryDto getCategory(Integer categoryid) {
		Category category=this.categoryrepo.findById(categoryid).orElseThrow(()-> new ResourceNotFoundException("category", "CategoryId", categoryid));
		return modelMapper.map(category, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getallcategories() {
		List<Category> categories=this.categoryrepo.findAll();
		List<CategoryDto> categoriesdtos=categories.stream().map((cat)-> this.modelMapper.map(cat, CategoryDto.class)).collect(Collectors.toList());
		return categoriesdtos;
	}

}

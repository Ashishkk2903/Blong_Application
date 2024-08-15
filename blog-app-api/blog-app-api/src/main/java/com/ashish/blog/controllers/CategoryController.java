package com.ashish.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ashish.blog.payloads.CategoryDto;
import com.ashish.blog.payloads.apiresponce;
import com.ashish.blog.services.CategoryService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/api/categories")
public class CategoryController 
{
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createcategory(@RequestBody @Valid CategoryDto categoryDto)
	{
		CategoryDto catdto=this.categoryService.createCategory(categoryDto);
		return new ResponseEntity<CategoryDto>(catdto, HttpStatus.CREATED);
	}
	
	@PutMapping("/{categoryid}")
	public ResponseEntity<CategoryDto> updateCategory(@RequestBody @Valid CategoryDto categoryDto,@PathVariable Integer categoryid)
	{
		CategoryDto updatedDto=this.categoryService.updateCategory(categoryDto, categoryid);
		return new ResponseEntity<CategoryDto>(updatedDto,HttpStatus.OK);
	}
	
	@DeleteMapping("/{categoryid}")
	public ResponseEntity<apiresponce> deleteCategory(@PathVariable Integer categoryid)
	{
		this.categoryService.deleteCategory(categoryid);
		return new ResponseEntity<apiresponce>(new apiresponce("category is successfully deleted",true),HttpStatus.OK);
	}
	
	@GetMapping("/{categoryid}")
	public ResponseEntity<CategoryDto> getCategory(@PathVariable Integer categoryid)
	{
		CategoryDto getCategory=this.categoryService.getCategory(categoryid);
		return new ResponseEntity<CategoryDto>(getCategory,HttpStatus.OK);
	}
	
	
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getallCategory()
	{
		List<CategoryDto> categorydtoList=this.categoryService.getallcategories();
		return ResponseEntity.ok(categorydtoList);
	}
}

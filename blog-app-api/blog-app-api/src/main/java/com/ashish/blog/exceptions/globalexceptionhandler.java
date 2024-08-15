package com.ashish.blog.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ashish.blog.payloads.apiresponce;

@RestControllerAdvice
public class globalexceptionhandler
{
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<apiresponce> resourcenotfound(ResourceNotFoundException ex)
	{
		String message=ex.getMessage();
		apiresponce apiresponce=new apiresponce(message, false);
		return new ResponseEntity<apiresponce>(apiresponce,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>>handelmethodargsnotvalidexception(MethodArgumentNotValidException ex)
	{
		Map<String, String> resp=new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error)->{
			String field=((FieldError)error).getField();
			String defaultmessage=error.getDefaultMessage();
			resp.put(field,defaultmessage);
		});
		return new ResponseEntity<Map<String,String>>(resp,HttpStatus.BAD_REQUEST); 
	}
	
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<apiresponce> methodnotsupported(HttpRequestMethodNotSupportedException hr)
	{
		String message=hr.getMessage();
		apiresponce apiresponce=new apiresponce(message,false);
		return new ResponseEntity<apiresponce>(apiresponce,HttpStatus.METHOD_NOT_ALLOWED);
	}
	
}

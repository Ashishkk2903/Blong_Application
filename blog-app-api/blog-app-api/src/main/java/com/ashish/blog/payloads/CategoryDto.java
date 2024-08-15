package com.ashish.blog.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor
@Getter
public class CategoryDto 
{
	private int categoryid;
	
	@NotEmpty
	@Size(min=4)
	private String categoryTitle;
	
	@NotEmpty
	@Size(min=10)
	private String categoryDescription;

}

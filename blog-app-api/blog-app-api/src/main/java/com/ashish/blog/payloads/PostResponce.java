package com.ashish.blog.payloads;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostResponce 
{
	private List<PostDto> content;
	private int pageNumber;
	private int pageSize;
	private long totalPages;
	private long totalElements;
	private boolean lastpage;
}

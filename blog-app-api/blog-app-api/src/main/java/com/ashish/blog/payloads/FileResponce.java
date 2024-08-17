package com.ashish.blog.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileResponce {
    private String filename;
    private String message;
	public FileResponce(String filename, String message) {
		super();
		this.filename = filename;
		this.message = message;
	}
}

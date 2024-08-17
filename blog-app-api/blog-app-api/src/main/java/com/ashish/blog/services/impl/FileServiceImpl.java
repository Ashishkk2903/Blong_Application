package com.ashish.blog.services.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import javax.swing.JPopupMenu.Separator;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ashish.blog.services.FileService;

@Service
public class FileServiceImpl implements FileService {

	@Override
	public String uploadImage(String path, MultipartFile file) throws IOException {
		String name=file.getOriginalFilename();
		String randomid=UUID.randomUUID().toString();
		String filename=randomid.concat(name.substring(name.lastIndexOf('.')));
		String fullpath=path+File.separator+filename;
		File f=new File(path);
		if(!f.exists())
			f.mkdir();
		
		Files.copy(file.getInputStream(), Paths.get(fullpath));
		
		return filename;
	}

	@Override
	public InputStream getresource(String path, String filename) throws FileNotFoundException {
		String fullpath=path+File.separator+filename;
		InputStream pathStream=new FileInputStream(fullpath);
		return pathStream;
	}

}

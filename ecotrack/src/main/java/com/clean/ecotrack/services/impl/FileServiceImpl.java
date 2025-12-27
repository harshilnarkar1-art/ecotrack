package com.clean.ecotrack.services.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import javax.imageio.stream.FileImageInputStream;

import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.clean.ecotrack.services.FileService;


@Service
public class FileServiceImpl implements FileService {



	@Override
	public String uploadFile(MultipartFile multipartFile, String path) throws IOException {
		String originalFilename = multipartFile.getOriginalFilename();
		
		String newFileName = UUID.randomUUID().toString();
		String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
		String newName = newFileName + extension;
		
		if(extension.equalsIgnoreCase(".png") || extension.equalsIgnoreCase(".jpeg") || extension.equalsIgnoreCase(".jpg")) 
		{
			File folder = new File(path);
			if(!folder.exists())
			{
				folder.mkdirs();
			}
			
				Files.copy(multipartFile.getInputStream(), Paths.get(path + newName));
				return newName;
			
			
		}
		else {
			throw new RuntimeException("File with "+extension+" is not allowed");
		}
	}

	
	@Override
	public InputStream getResource(String path, String name) throws FileNotFoundException {
		FileInputStream inputStream = new FileInputStream(path+name);
		return inputStream;
	}

}

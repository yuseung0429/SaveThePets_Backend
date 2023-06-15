package com.savethepets.utility;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class Utilities {
	
	public static File convertMultipartFileToFile(MultipartFile multipartFile) {
	    try {
	    	File file = File.createTempFile("temp", Utilities.getExtension(multipartFile.getOriginalFilename()));
			multipartFile.transferTo(file);
			return file;
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
	    return null;
	}

	public static List<File> convertMultipartFileListToFileList(List<MultipartFile> multipartFiles) {
		List<File> files = new ArrayList<File>();
		for(int i=0; i<multipartFiles.size(); i++)
		{
			MultipartFile multipartFile = multipartFiles.get(i);
			try {
				File file = File.createTempFile("temp", Utilities.getExtension(multipartFile.getOriginalFilename()));
				multipartFile.transferTo(file);
				files.add(file);
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		}
		return files;
	}
	
	public static String getExtension(String filename)
	{
		return filename.substring(filename.lastIndexOf("."));
	}
	
}

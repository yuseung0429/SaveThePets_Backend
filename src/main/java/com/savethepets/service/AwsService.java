package com.savethepets.service;

import java.io.File;

public interface AwsService {
	
	public String save(File file, String dir, String filename, String extention);
	void remove(String file);

	
}

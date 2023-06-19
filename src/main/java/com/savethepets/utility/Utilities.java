package com.savethepets.utility;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

/**
 * Description<br>
 *  - Utilities Class : 다른 클래스에서 범용적으로 사용되는 여러가지 메소드 집합<br>
 * <br>
 * Method<br>
 *  - convertMultipartFIleToFile : multipartFile을 File로 변환하는 메소드<br>
 *  - convertMultipartFileListToFileList : multipartFile List를 File List로 변환하는 메소드<br>
 *  - getExtension : 파일 이름에 대한 확장자를 반환하는 메소드<br>
 * @author Yuseung lee.
 * @since 2023.06.19
 */
public class Utilities {
	/**
	 * Description<br>
	 *  - convertMultipartFIleToFile : multipartFile을 File로 변환하는 메소드<br>
	 * @param multipartFile MultipartFile
	 * @return MultipartFile에서 변환된 File
	 * @author Yuseung lee.
	 * @since 2023.06.19
	 */
	public static File convertMultipartFileToFile(MultipartFile multipartFile) {
	    try {
	    	File file = File.createTempFile("temp", Utilities.getExtension(multipartFile.getOriginalFilename()));
			multipartFile.transferTo(file);
			return file;
		} catch (IllegalStateException | IOException e) {
			return null;
		}
	}
	/**
	 * Description<br>
	 *  - convertMultipartFileListToFileList : multipartFile List를 File List로 변환하는 메소드<br>
	 * @param multipartFiles MultipartFile List
	 * @return MultipartFile List에서 변환된 File List
	 * @author Yuseung lee.
	 * @since 2023.06.19
	 */
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
	/**
	 * Description<br>
	 *  - convertMultipartFileListToFileList : multipartFile List를 File List로 변환하는 메소드<br>
	 * Example<br>
	 *  - ".exe", ".png", ".jpg" <br>
	 * @param fname 파일의 이름 (확장자 포함)
	 * @return 해당 파일의 확장자("."포함) 또는 null("."미발견 시)
	 * @author Yuseung lee.
	 * @since 2023.06.19
	 */
	public static String getExtension(String fname)
	{
		int index = fname.lastIndexOf(".");
		if(index != -1)
			return fname.substring(fname.lastIndexOf("."));
		else
			return null;
	}
	
}

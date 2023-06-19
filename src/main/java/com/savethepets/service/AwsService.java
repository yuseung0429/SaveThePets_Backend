package com.savethepets.service;

import java.io.File;
/**
 * Description<br>
 *  - AlarmService Interface<br>
 * <br>
 * Method <br>
 *  - save : AWS S3에 데이터를 저장하는 메소드 <br>
 * 	- remove : AWS S3에 데이터를 삭제하는 메소드 <br>
 * @author Yuseung lee.
 * @since 2023.06.19
 */
public interface AwsService {
	public String save(File file, String dir, String filename, String extention);
	void remove(String file);
}

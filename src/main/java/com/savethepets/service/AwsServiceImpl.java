package com.savethepets.service;

import java.io.File;
import java.util.List;

import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;

import lombok.RequiredArgsConstructor;

/**
 * Description<br>
 *  - AwsServiceImpl Class : AwsService를 구현한 구현체 클래스<br>
 * <br>
 * Field<br>
 * 	- amazonS3 : AWS S3 Service 접근을 위한 Object<br>
 *  - bucket : AWS Bucket 이름<br>
 * <br>
 * Method <br>
 *  - save : AWS S3에 데이터를 저장하는 메소드 <br>
 * 	- remove : AWS S3에 데이터를 삭제하는 메소드 <br>
 * @author Yuseung lee.
 * @since 2023.06.19
 */
@RequiredArgsConstructor
@Component
@Service
public class AwsServiceImpl implements AwsService{
    private final AmazonS3 amazonS3;
    
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;
    
	/**
	 * Description<br>
	 *  - save : AWS S3에 데이터를 저장하는 메소드<br>
	 * @param file 저장할 File Object
	 * @param dir 파일이 저장될 디렉토리 주소
	 * @param filename 파일 이름
	 * @param extention 파일 확장자
	 * @return 파일이 저장된 URL
	 * @author Yuseung lee.
	 * @since 2023.06.19
	 */
    @Override
    public String save(File file, String dir, String filename, String extention){
    	String fileName = dir + "/" + filename + extention;
    	amazonS3.putObject(
                new PutObjectRequest(bucket, fileName, file)
                        .withCannedAcl(CannedAccessControlList.PublicRead)
        );
    	file.delete();
        return amazonS3.getUrl(bucket, fileName).toString();
    }

    @Override
    public void remove(String file) {
        ObjectListing objectListing = amazonS3.listObjects(bucket, file);
        List<S3ObjectSummary> objectSummaries = objectListing.getObjectSummaries();

        for (S3ObjectSummary objectSummary : objectSummaries) {
            amazonS3.deleteObject(bucket, objectSummary.getKey());
        }

        if (objectListing.isTruncated()) {
            remove(file); // 재귀적으로 호출하여 나머지 객체 삭제
        } else {
            amazonS3.deleteObject(bucket, file + "/"); // 디렉토리 삭제
        }
    }
}
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
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
@Service
public class AwsServiceImpl implements AwsService{
    private final AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

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
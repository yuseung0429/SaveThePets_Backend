package com.savethepets.service;

import java.io.File;

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
    public void remove(String file){
    	amazonS3.deleteObject(bucket, file);
    }
}
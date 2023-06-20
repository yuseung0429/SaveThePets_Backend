package com.savethepets.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

import lombok.Getter;

/**
 * Description<br>
 *  - AwsConfig Class : AWS S3 관련 Configuration 클래스<br>
 * <br>
 * Field<br>
 *  - accessKey : 버킷 AccessKey  <br>
 *  - secretKey : 버킷 SecretKey <br>
 *  - region : 버킷 지역<br>
 * <br>
 * Method<br>
 *  - amazonS3Client : AWS S3를 사용할 수 있게 하는 Object 생성<br>
 * @author Yuseung lee.
 * @since 2023.06.19
 */
@Configuration
public class AwsConfig {
    @Value("${cloud.aws.credentials.access-key}")
    private String accessKey;

    @Value("${cloud.aws.credentials.secret-key}")
    private String secretKey;

    @Value("${cloud.aws.region.static}")
    private String region;

    @Bean
    public AmazonS3 amazonS3Client() {
        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);
        return AmazonS3ClientBuilder.standard()
                .withRegion(region)
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();
    }
}
package com.doctor.spa.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
@ComponentScan({"com.doctor.spa"})
@PropertySource("classpath:aws.properties")
public class AmazonConfiguration {

	@Autowired
	private Environment env;

    @Bean
    public AmazonS3 getAmazonS3Cient() {
        AWSCredentials awsCredentials =
                new BasicAWSCredentials(env.getProperty("aws.iam.accessKey"),
                						env.getProperty("aws.iam.secretKey"));
        return AmazonS3ClientBuilder
                .standard()
                .withRegion(env.getProperty("aws.s3.bucket.region"))
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();

    }
}

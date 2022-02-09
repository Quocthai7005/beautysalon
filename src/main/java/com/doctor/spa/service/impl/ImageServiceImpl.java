package com.doctor.spa.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.doctor.spa.service.ImageService;

@Service
public class ImageServiceImpl implements ImageService {

	@Autowired
	private AmazonS3 amazonS3;
	
	@Value( "${aws.s3.bucket.name}" )
	private String bucketName;

	@Override
    // @Async annotation ensures that the method is executed in a different background thread 
    // but not consume the main thread.
    @Async
    public String uploadFile(final MultipartFile multipartFile) {
		String path = null;
        try {
            final File file = convertMultiPartFileToFile(multipartFile);
            path = uploadFileToS3Bucket(bucketName, file);
            file.delete();  // To remove the file locally created in the project folder.
            return path;
        } catch (final AmazonServiceException ex) {
            System.out.println("File upload is failed.");
            System.out.println("Error= {} while uploading file." + ex.getMessage());
        }
        return path;
    }
 
    private File convertMultiPartFileToFile(final MultipartFile multipartFile) {
        final File file = new File(multipartFile.getOriginalFilename());
        try (final FileOutputStream outputStream = new FileOutputStream(file)) {
            outputStream.write(multipartFile.getBytes());
        } catch (final IOException ex) {
        	System.out.println("Error converting the multi-part file to file= " + ex.getMessage());
        }
        return file;
    }
 
    private String uploadFileToS3Bucket(final String bucketName, final File file) {
        System.out.println("Uploading file with name= " + file.getName());
        
        final PutObjectRequest putObjectRequest = new PutObjectRequest(
        		bucketName, file.getName(), file).withCannedAcl(CannedAccessControlList.PublicRead);
        amazonS3.putObject(putObjectRequest);
        return amazonS3.getUrl("mypetswebsitebucket", file.getName()).getPath();
    }
}

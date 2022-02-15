package com.doctor.spa.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ListObjectsV2Request;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.doctor.spa.service.AwsS3Service;

@Service
public class AwsS3ServiceImpl implements AwsS3Service {

	@Autowired
	private AmazonS3 amazonS3;

	@Value("${aws.s3.bucket.name}")
	private String bucketName;

	@Value("${aws.s3.bucket.news}")
	private String newsPrefix;

	@Value("${aws.s3.bucket.product}")
	private String productPrefix;

	@Value("${aws.iam.accessKey}")
	private String accessKey;

	@Value("${aws.iam.secretKey}")
	private String secretKey;

	@Override
	// @Async annotation ensures that the method is executed in a different
	// background thread
	// but not consume the main thread.
	@Async
	public String uploadFile(final MultipartFile multipartFile) {
		String path = null;
		try {
			final File file = convertMultiPartFileToFile(multipartFile);
			path = uploadFileToS3Bucket(bucketName, file);
			file.delete(); // To remove the file locally created in the project folder.
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

		final PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, file.getName(), file)
				.withCannedAcl(CannedAccessControlList.PublicRead);
		amazonS3.putObject(putObjectRequest);
		return amazonS3.getUrl("mypetswebsitebucket", file.getName()).getPath();
	}

	@Override
	public List<S3ObjectSummary> getAllItems() {
		ListObjectsV2Result listObjectsV2Result = amazonS3.listObjectsV2(bucketName);
		List<S3ObjectSummary> S3ObjectSummaryList = listObjectsV2Result.getObjectSummaries();
		return S3ObjectSummaryList;
	}

	@Override
	public Page<S3ObjectSummary> getNewsImages(Pageable pageable, String lastKey) {
		return getImageWithPrefix(pageable, lastKey, newsPrefix);
	}

	@Override
	public Page<S3ObjectSummary> getProductImages(Pageable pageable, String lastKey) {
		return getImageWithPrefix(pageable, lastKey, productPrefix);
	}

	private Page<S3ObjectSummary> getImageWithPrefix(Pageable pageable, String lastKey, String prefix) {
		ListObjectsV2Request req = new ListObjectsV2Request();
		req.setMaxKeys(pageable.getPageSize());
		req.setStartAfter(lastKey);
		req.setBucketName(bucketName);
		req.setPrefix(prefix);
		ListObjectsV2Result listObjectsV2Result = amazonS3.listObjectsV2(req);
		List<S3ObjectSummary> s3ObjectSummaryList = listObjectsV2Result.getObjectSummaries();
		return new PageImpl<S3ObjectSummary>(s3ObjectSummaryList);
	}

	@Override
	public int getNewsImagesNo() {
		return getImageWithPrefix(newsPrefix);
	}

	@Override
	public int getProductImagesNo() {
		return getImageWithPrefix(productPrefix);
	}

	private int getImageWithPrefix(String prefix) {
		ListObjectsV2Request req = new ListObjectsV2Request();
		req.setBucketName(bucketName);
		req.setPrefix(prefix);
		ListObjectsV2Result listObjectsV2Result = amazonS3.listObjectsV2(req);
		List<S3ObjectSummary> s3ObjectSummaryList = listObjectsV2Result.getObjectSummaries();
		return s3ObjectSummaryList.size();
	}
}

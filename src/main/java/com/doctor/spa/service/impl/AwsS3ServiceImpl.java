package com.doctor.spa.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ListObjectsV2Request;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.doctor.spa.dto.NewsDto;
import com.doctor.spa.dto.ProductDto;
import com.doctor.spa.dto.S3ObjectDto;
import com.doctor.spa.dto.SubProductDto;
import com.doctor.spa.mapper.S3ObjectMapper;
import com.doctor.spa.service.AwsS3Service;
import com.doctor.spa.service.NewsService;
import com.doctor.spa.service.ProductService;
import com.doctor.spa.service.SubProductService;

@Service
public class AwsS3ServiceImpl implements AwsS3Service {

	@Autowired
	private AmazonS3 amazonS3;

	@Autowired
	private NewsService newService;

	@Autowired
	private ProductService productService;
	
	@Autowired
	private SubProductService subProductService;

	@Value("${aws.s3.bucket.name}")
	private String bucketName;

	@Value("${aws.iam.accessKey}")
	private String accessKey;

	@Value("${aws.iam.secretKey}")
	private String secretKey;
	
	@Value("${aws.s3.bucket.news}")
	private String newsDir;
	
	@Value("${aws.s3.bucket.product}")
	private String productDir;
	
	@Value("${aws.s3.bucket.subproduct}")
	private String subProductDir;
	
	@Autowired
	private S3ObjectMapper s3ObjectMapper;

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

	private Map<String, List<NewsDto>> getKeyWithUsedInNews() {
		Map<String, List<NewsDto>> pairs = new HashMap<>();
		List<NewsDto> newsDtos = newService.getAll();
		for (NewsDto dto : newsDtos) {
			List<NewsDto> dtos = pairs.get(dto.getImageKey());
			if (dtos == null) {
				List<NewsDto> usedDto = new ArrayList<NewsDto>();
				usedDto.add(dto);
				pairs.put(dto.getImageKey(), usedDto);
			} else {
				dtos.add(dto);
			}
		}
		return pairs;
	}

	private Map<String, List<ProductDto>> getKeyWithUsedInProduct() {
		Map<String, List<ProductDto>> pairs = new HashMap<>();
		List<ProductDto> productDtos = productService.getAll();
		for (ProductDto dto : productDtos) {
			List<ProductDto> dtos = pairs.get(dto.getImageKey());
			if (dtos == null) {
				List<ProductDto> usedDto = new ArrayList<ProductDto>();
				usedDto.add(dto);
				pairs.put(dto.getImageKey(), usedDto);
			} else {
				dtos.add(dto);
			}
		}
		return pairs;
	}

	private Map<String, List<SubProductDto>> getKeyWithUsedInSubProduct() {
		Map<String, List<SubProductDto>> pairs = new HashMap<>();
		List<SubProductDto> subProductDtos = subProductService.getAll();
		for (SubProductDto dto : subProductDtos) {
			List<SubProductDto> dtos = pairs.get(dto.getImageKey());
			if (dtos == null) {
				List<SubProductDto> usedDto = new ArrayList<SubProductDto>();
				usedDto.add(dto);
				pairs.put(dto.getImageKey(), usedDto);
			} else {
				dtos.add(dto);
			}
		}
		return pairs;
	}

	private String getUsername() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			return ((UserDetails) principal).getUsername();
		} else {
			return principal.toString();
		}
	}

	private String uploadFileToS3Bucket(final String bucketName, final File file) {
		System.out.println("Uploading file with name= " + file.getName());

		final PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, file.getName(), file)
				.withCannedAcl(CannedAccessControlList.PublicRead);
		ObjectMetadata md = new ObjectMetadata();
		md.addUserMetadata("uploader", getUsername());
		putObjectRequest.setMetadata(md);
		amazonS3.putObject(putObjectRequest);
		return amazonS3.getUrl(bucketName, file.getName()).getPath();
	}

	@Override
	public List<S3ObjectSummary> getAllItems() {
		ListObjectsV2Result listObjectsV2Result = amazonS3.listObjectsV2(bucketName);
		List<S3ObjectSummary> S3ObjectSummaryList = listObjectsV2Result.getObjectSummaries();
		return S3ObjectSummaryList;
	}

	@Override
	public Page<S3ObjectDto> getFiles(Pageable pageable, String directory) {
		ListObjectsV2Request req = new ListObjectsV2Request();
		req.setBucketName(bucketName);
		req.setPrefix(directory);
		ListObjectsV2Result listObjectsV2Result = amazonS3.listObjectsV2(req);
		List<S3ObjectSummary> s3ObjectSummaryList = listObjectsV2Result.getObjectSummaries();
		int from = pageable.getPageNumber() * pageable.getPageSize();
		int to = (from + pageable.getPageSize()) > (s3ObjectSummaryList.size() - 1) ? (s3ObjectSummaryList.size() - 1)
				: (from + pageable.getPageSize());
		List<S3ObjectSummary> sublist = s3ObjectSummaryList.subList(from, to);
		List<S3ObjectDto> dtos = new ArrayList<>();

		if (directory.equals(newsDir)) {
			Map<String, List<NewsDto>> pairs = getKeyWithUsedInNews();
			for (S3ObjectSummary obj : sublist) {
				if ((directory + "/").equals(obj.getKey()))
					continue;
				S3ObjectDto dto = s3ObjectMapper.toDto(obj);
				dto.setUsedInNews(pairs.get(obj.getKey()));
				dtos.add(dto);
			}
		} else if (directory.equals(productDir)) {
			Map<String, List<ProductDto>> pairs = getKeyWithUsedInProduct();
			for (S3ObjectSummary obj : sublist) {
				if ((directory + "/").equals(obj.getKey()))
					continue;
				S3ObjectDto dto = s3ObjectMapper.toDto(obj);
				dto.setUsedInProduct(pairs.get(obj.getKey()));
				dtos.add(dto);
			}
		} else if (directory.equals(subProductDir)) {
			Map<String, List<SubProductDto>> pairs = getKeyWithUsedInSubProduct();
			for (S3ObjectSummary obj : sublist) {
				if ((directory + "/").equals(obj.getKey()))
					continue;
				S3ObjectDto dto = s3ObjectMapper.toDto(obj);
				dto.setUsedInSubProduct(pairs.get(obj.getKey()));
				dtos.add(dto);
			}
		}
		return new PageImpl<S3ObjectDto>(dtos);
	}

	@Override
	public int getFilesNo(String prefix) {
		ListObjectsV2Request req = new ListObjectsV2Request();
		req.setBucketName(bucketName);
		req.setPrefix(prefix);
		ListObjectsV2Result listObjectsV2Result = amazonS3.listObjectsV2(req);
		List<S3ObjectSummary> s3ObjectSummaryList = listObjectsV2Result.getObjectSummaries();
		return s3ObjectSummaryList.size();
	}

	@Override
	public boolean deleteFile(String key) {
		if (key == null) {
			return false;
		}
		DeleteObjectRequest req = new DeleteObjectRequest(bucketName, key);
		amazonS3.deleteObject(req);
		return true;
	}

}

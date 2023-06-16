package com.green.nowon.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3Client;
import com.green.nowon.domain.dto.ItemSaveDTO;
import com.green.nowon.domain.entity.ItemEntity;
import com.green.nowon.domain.entity.ItemEntityRepository;
import com.green.nowon.domain.entity.ItemImageEntity;
import com.green.nowon.domain.entity.ItemImageEntityRepository;
import com.green.nowon.service.AdminService;
import com.green.nowon.utils.FileUploadUtil;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AdminServiceProcess implements AdminService{
	
	//private final FileUploadUtil fileUpload;
	private final AmazonS3Client S3Client;
	
	@Value("${cloud.aws.s3.bucket.temp}")
	private String TEMP_PATH;
	@Value("${cloud.aws.s3.bucket.upload}")
	private String UPLOAD_PATH;
	
	@Value("${cloud.aws.s3.bucket}")
	private String BUCKET;
	
	private final ItemImageEntityRepository imageRepo;
	private final ItemEntityRepository itemRepo;
	//미리보기 이미지
	@Override
	public Map<String, String> tempUpload(MultipartFile tempImg) {
		return FileUploadUtil.s3Upload(S3Client, BUCKET,TEMP_PATH ,tempImg);
		
		//return  FileUploadUtil.classPathUpload("/images/upload/temp/", tempImg);
	}

	//상품, 리스트이미지저장
	@Override
	public void saveProcess(ItemSaveDTO dto) {
		//ItemEntity item=itemRepo.save(dto.toItemEntity());
		String uploadKey=UPLOAD_PATH+dto.getOrgName();
		String url=FileUploadUtil.s3TempToSrc(S3Client, BUCKET,dto.getBucketKey(),uploadKey);
		
		imageRepo.save(ItemImageEntity.builder()
				.isList(true).isdef(true)
				.url(url)
				.bucketKey(uploadKey)
				.item(itemRepo.save(dto.toItemEntity()))//fk ItemEntity객체로
				.build());
		
	}

}

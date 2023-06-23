package com.green.nowon.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3Client;
import com.green.nowon.domain.dto.ItemImgSaveDTO;
import com.green.nowon.domain.dto.ItemListDTO;
import com.green.nowon.domain.dto.ItemSaveDTO;
import com.green.nowon.domain.entity.CategoryEntityRepository;
import com.green.nowon.domain.entity.ItemEntity;
import com.green.nowon.domain.entity.ItemEntityRepository;
import com.green.nowon.domain.entity.ItemImageEntity;
import com.green.nowon.domain.entity.ItemImageEntityRepository;
import com.green.nowon.service.AdminService;
import com.green.nowon.utils.FileUploadUtil;

import lombok.RequiredArgsConstructor;
import lombok.val;

@RequiredArgsConstructor
@Service
public class AdminServiceProcess implements AdminService{
	
	//private final FileUploadUtil fileUpload;
	
	private final AmazonS3Client s3Client;
	private final ItemImageEntityRepository imageRepo;
	private final ItemEntityRepository itemRepo;
	private final CategoryEntityRepository cateRepo;
	
	@Value("${cloud.aws.s3.bucket.temp}")
	private String TEMP_PATH;
	@Value("${cloud.aws.s3.bucket.upload}")
	private String UPLOAD_PATH;
	
	@Value("${cloud.aws.s3.bucket}")
	private String BUCKET;
	
	
	

	@Override
	public Map<String, String> tempUpload(MultipartFile tempImg) {
		System.out.println(">>>:"+s3Client);
		return FileUploadUtil.s3Upload(s3Client, BUCKET, TEMP_PATH , tempImg);
		
		//return FileUploadUtil.classPathUpload("/images/upload/temp/",tempImg);
		
	}
	
	@Override
	public void saveProcess(ItemSaveDTO itemDto, ItemImgSaveDTO imgDto, long categoryNo) {
		
		ItemEntity item= itemRepo.save(itemDto.toItemEntity()
				.addCategory(cateRepo.findById(categoryNo).orElseThrow()));
		
		String[] bucketKeies=imgDto.getBucketKey();
		String[] orgNames=imgDto.getOrgName();
		String[] newNames=imgDto.getNewName();
		for(int i=0; i<bucketKeies.length; i++) {
			if(bucketKeies[i]==null || bucketKeies[i]=="")continue;
			String newName=newNames[i];
			String orgName=orgNames[i];
			String uploadKey=UPLOAD_PATH+newName;
			String url=FileUploadUtil.s3TempToSrc(s3Client, BUCKET, bucketKeies[i], uploadKey);
			if(i==0) {

				imageRepo.save(ItemImageEntity.builder()
						.bucketKey(bucketKeies[i])
						.isDef(true).isList(true)
						.url(url).orgName(orgName).newName(newName)
						.item(item)
						.build());
			}else {
			
				imageRepo.save(ItemImageEntity.builder()
						.bucketKey(bucketKeies[i])
						.isList(true)
						.url(url).orgName(orgName).newName(newName)
						.item(item)
						.build());
			}
			
			
		}
		
		
	}

	//상품, 리스트이미지 저장
	@Override
	public void saveProcess(ItemSaveDTO dto) {
		//ItemEntity item=itemRepo.save(dto.toItemEntity());
		//이미지는 현재 temp-> 이동
		String uploadKey=UPLOAD_PATH+dto.getNewName();
		String url=FileUploadUtil.s3TempToSrc(s3Client, BUCKET, dto.getBucketKey(), uploadKey);
		
		imageRepo.save(ItemImageEntity.builder()
				.isList(true).isDef(true)
				.url(url)
				.orgName(dto.getOrgName())
				.newName(dto.getNewName())
				.bucketKey(uploadKey)
				.item(itemRepo.save(dto.toItemEntity()))//FK ItemEntity객체로
				.build());
		
	}
	
	
	@Override
	public void findAllProcess(Model model) {
		model.addAttribute("list", itemRepo.findAll().stream()
													.map(item->new ItemListDTO(item)
															.defImg(imageRepo.findByItemAndIsListAndIsDef(item,true,true)
																	.orElse(ItemImageEntity.builder()
																			.url("/images/common/noimg.jpg")
																			.build())))
													/*
													.map(item->{
														ItemListDTO dto=new ItemListDTO(item);
														dto.defImg(imageRepo.findByItemAndIsListAndIsDef(item,true,true).get());
														return dto;})
													//*/
													.collect(Collectors.toList()) //List<ItemListDTO>
											);
	}

	

	
	
	/*
	@Override
	public void findAllProcess(Model model) {
		//1. 아이템 목록 갖고오기
		List<ItemEntity> itemResult=itemRepo.findAll();
		
		//2. 아이템-이미지 갖고오기
		List<ItemListDTO> result=new ArrayList<>();
		for(ItemEntity item :itemResult) {
			ItemListDTO itemList=new ItemListDTO(item);
			ItemImageEntity defImg=imageRepo.findByItemAndIsListAndIsDef(item,true,true).get();
			itemList.defImg(defImg);
			result.add(itemList);
		}
		model.addAttribute("list", result);
	}
	*/

}

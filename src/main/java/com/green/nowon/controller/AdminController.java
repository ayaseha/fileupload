package com.green.nowon.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.green.nowon.domain.dto.ItemImgSaveDTO;
import com.green.nowon.domain.dto.ItemImgSaveDTO2;
import com.green.nowon.domain.dto.ItemSaveDTO;
import com.green.nowon.domain.dto.ItemSaveDTO2;
import com.green.nowon.service.AdminService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class AdminController {
	
	private final AdminService service;
	
	@ResponseBody
	@GetMapping("/test")
	public ResponseEntity<String> promiseTest() throws Exception {
		
		return ResponseEntity.ok().body("success");
	}

	@GetMapping("/admin")
	public String adminIndex() {
		return "admin/default";
	}
	
	/*
	//상품등록-이미지
	@PostMapping("/admin/item")
	public String adminItem(ItemSaveDTO dto) {
		service.saveProcess(dto);
		return "redirect:/admin/item";
	}
	//*/
	//*
	//상품등록-이미지
	@PostMapping(value = "/admin/item")
	public String adminItem(Long[] categoryNo,ItemSaveDTO itemDto,ItemImgSaveDTO imgDto) {
		//System.out.println(">>>>>>>:"+Arrays.toString(categoryNo));
		//현재 카테고리의 특징이 셀프조인이기에 최하위카테고리값이 가장 큰값을 갖는 구조임.
//		long maxNo=Arrays.asList(categoryNo).stream().max(Long::compareTo).get();
//		System.out.println(">>>>>>: "+maxNo);
		service.saveProcess(itemDto, imgDto,Arrays.asList(categoryNo).stream().max(Long::compareTo).get());
		return "redirect:/admin/item";
	}
	//*/
	
	@ResponseBody
	@PostMapping(value = "/admin/item/test")
	public void adminItem(String dtos) {
		System.out.println(dtos);
		
	}
	
	//상품리스트 페이지
	@GetMapping("/admin/item")
	public String adminItem(Model model) {
		service.findAllProcess(model);
		return "admin/item/list";
	}
	
	
	@GetMapping("/admin/item/new")
	public String adminItemNew() {
		return "admin/item/write";
	}
	
	@PostMapping("/admin/item/temp")
	@ResponseBody
	public ResponseEntity<Map<String, String>> tempUpload(MultipartFile tempImg){
		return ResponseEntity.ok()
				.body(service.tempUpload(tempImg));//success: function(result){/*result로 path 전달*/}
	}
}

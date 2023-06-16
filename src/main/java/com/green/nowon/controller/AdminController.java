package com.green.nowon.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.green.nowon.domain.dto.ItemSaveDTO;
import com.green.nowon.service.AdminService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class AdminController {
	
	private final AdminService service;
	
	
	@GetMapping("/admin")
	public String adminIndex() {
		return "admin/default";
	}
	
	@GetMapping("/admin/item")
	public String adminItem() {
		return "admin/item/list";
	}
	
	@GetMapping("/admin/item/new")
	public String adminItemNew() {
		return "admin/item/write";
	}
	
	@PostMapping("/admin/item")
	public String adminItemSave(ItemSaveDTO dto) {
		service.saveProcess(dto);
		return "admin/item/list";
	}
	
	
	@PostMapping("/admin/item/temp")
	@ResponseBody
	public ResponseEntity<Map<String, String>> tempUpload(MultipartFile tempImg) {
		return ResponseEntity.ok()
				.body(service.tempUpload(tempImg)); //success: function(result){/* result로 path 전달 */}
	}
}

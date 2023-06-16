package com.green.nowon.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.green.nowon.domain.dto.ItemSaveDTO;

public interface AdminService {

	Map<String, String> tempUpload(MultipartFile tempImg);

	void saveProcess(ItemSaveDTO dto);

}

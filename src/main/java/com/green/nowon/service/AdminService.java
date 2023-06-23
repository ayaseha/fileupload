package com.green.nowon.service;

import java.util.Map;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.green.nowon.domain.dto.ItemImgSaveDTO;
import com.green.nowon.domain.dto.ItemSaveDTO;

public interface AdminService {

	Map<String, String> tempUpload(MultipartFile tempImg);

	void saveProcess(ItemSaveDTO dto);

	void findAllProcess(Model model);

//	void saveProcess(ItemSaveDTO itemDto, ItemImgSaveDTO imgDto);

	void saveProcess(ItemSaveDTO itemDto, ItemImgSaveDTO imgDto, long categoryNo);


}

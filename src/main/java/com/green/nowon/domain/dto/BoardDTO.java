package com.green.nowon.domain.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
public class BoardDTO {
	
	private long no;//자동
	private String subject;//form
	private String content;//form
	private String writer;//form
	private int readCount;//0
	private String userIp;
	private LocalDateTime createdDate;//자동
	private LocalDateTime updatedDate;//자동

}

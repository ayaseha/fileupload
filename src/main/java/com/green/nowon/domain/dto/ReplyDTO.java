package com.green.nowon.domain.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ReplyDTO {
	
	private long no;
	private String content;
	private String writer;
	private int readCount;
	private LocalDateTime createdDate;
	private LocalDateTime updatedDate;
	private long boardNo;
}

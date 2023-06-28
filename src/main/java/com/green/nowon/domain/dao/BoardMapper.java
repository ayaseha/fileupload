package com.green.nowon.domain.dao;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.green.nowon.domain.dto.BoardDTO;

@Mapper // board-mapper-xml 파일을 구현class라 생각하고 처리하면 쉬움
public interface BoardMapper {

	
	List<BoardDTO> findAll(@Param("limit") int limit,@Param("offset") int offset);

	void save(BoardDTO dto);
	
	//@Select("select count(*) from board")
	int countAll();

	//@Select("select * from board where no=#{no}")
	Optional<BoardDTO> findByNo(@Param("no") long no);
	
}

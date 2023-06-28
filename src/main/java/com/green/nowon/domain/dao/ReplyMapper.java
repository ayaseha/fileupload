package com.green.nowon.domain.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.green.nowon.domain.dto.ReplyDTO;

@Mapper
public interface ReplyMapper {

	//@Insert("insert into reply(content, writer, board_no) values(#{content},#{writer},#{boardNo})")
	void save(ReplyDTO dto);
	
	@Select("select * from reply where board_no=#{boardNo} order by no desc")
	List<ReplyDTO> findAllbyBoardNo(long no);

}

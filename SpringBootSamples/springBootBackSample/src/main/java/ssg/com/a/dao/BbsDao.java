package ssg.com.a.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import ssg.com.a.dto.BbsDto;
import ssg.com.a.dto.BbsParam;
import ssg.com.a.dto.BbsComment;


@Mapper
@Repository
public interface BbsDao {

	List<BbsDto> bbsList(BbsParam param);
	int getAllBbs(BbsParam param);
	
	int bbsWriteAf(BbsDto dto);
	
	BbsDto bbsDetail(int seq);
	
	int bbsUpdate(BbsDto dto);
	
	int bbsDelete(BbsDto dto);
	
	void bbsAnswerUpdate(BbsDto bbs);
	int bbsAnswerInsert(BbsDto bbs);
	
	int commentWrite(BbsComment comment);
	List<BbsComment> commentList(BbsParam param);
	int getAllComment(int seq);
	BbsComment commentGet(BbsComment comment);
	int commentDelete(BbsComment comment);
	
	int commentAnswer(BbsComment comment);
	void commentAnswerUpdate(BbsComment comment);
}

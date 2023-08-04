package ssg.com.a.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import ssg.com.a.dto.BbsDto;
import ssg.com.a.dto.BbsParam;

@Mapper
@Repository
public interface BbsDao {

	List<BbsDto> bbsList(BbsParam param);
	int getAllBbs(BbsParam param);
	
	int bbsWriteAf(BbsDto dto);
	
	BbsDto bbsDetail(int seq);
	
	int bbsUpdate(BbsDto dto);
	
	void bbsAnswerUpdate(BbsDto bbs);
	int bbsAnswerInsert(BbsDto bbs);
}

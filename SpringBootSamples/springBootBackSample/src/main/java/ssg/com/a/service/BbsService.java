package ssg.com.a.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ssg.com.a.dao.BbsDao;
import ssg.com.a.dto.BbsDto;
import ssg.com.a.dto.BbsParam;
import ssg.com.a.dto.BbsComment;

@Service
@Transactional
public class BbsService {

	@Autowired
	BbsDao dao;
	
	public List<BbsDto> bbsList(BbsParam param){
		return dao.bbsList(param);
	}
	
	public int getAllBbs(BbsParam param) {
		return dao.getAllBbs(param);
	}
	
	public int bbsWriteAf(BbsDto dto) {
		return dao.bbsWriteAf(dto);
	}
	
	public BbsDto bbsDetail(int seq) {
		return dao.bbsDetail(seq);
	}
	
	public int bbsUpdate (BbsDto dto) {
		return dao.bbsUpdate(dto);
	}
	
	public int bbsDelete (BbsDto dto) {
		return dao.bbsDelete(dto);
	}
	
	public int bbsAnswer(BbsDto bbs) {
		dao.bbsAnswerUpdate(bbs);
		return dao.bbsAnswerInsert(bbs);
	}
	
	public int commentWrite(BbsComment comment) {		
		return dao.commentWrite(comment);
	}

	public List<BbsComment> commentList(BbsParam param) {		
		return dao.commentList(param);
	}

	public int getAllComment(int seq) {
		return dao.getAllComment(seq);
	}

	public BbsComment commentGet(BbsComment comment) {
		return dao.commentGet(comment);
	}

	public int commentDelete(BbsComment comment) {
		return dao.commentDelete(comment);
	}

	public int commentAnswer(BbsComment comment) {
		dao.commentAnswerUpdate(comment);
		return dao.commentAnswer(comment);
	}
}

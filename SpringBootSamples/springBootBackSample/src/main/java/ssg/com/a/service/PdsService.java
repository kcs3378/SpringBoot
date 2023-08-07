package ssg.com.a.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ssg.com.a.dao.PdsDao;
import ssg.com.a.dto.PdsDto;

@Service
@Transactional
public class PdsService {
	
	@Autowired
	PdsDao dao;
	
	public List<PdsDto> pdslist(){
		return dao.pdslist();
	}
	
	public PdsDto pdsget(int seq) {
		return dao.pdsget(seq);
	}
	
	public int pdswrite(PdsDto pds) {		
		return dao.pdswrite(pds);
	}
	
	public int pdsupload(PdsDto pds) {		
		return dao.pdsupload(pds);
	}
	
	public PdsDto pdsdetail(int seq) {
		return dao.pdsdetail(seq);
	}
	
	public int pdsupdate(PdsDto dto) {
		return dao.pdsupdate(dto);
	}
}

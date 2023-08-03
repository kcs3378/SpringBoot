package ssg.com.a.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ssg.com.a.dao.MemberDao;
import ssg.com.a.dto.MemberDto;

@Service
@Transactional
public class MemberService {

	@Autowired
	MemberDao dao;
	
	public int idcheck(String id) {
		return dao.idcheck(id);
	}
	
	public int addMember(MemberDto mem) {
		return dao.addMember(mem);
	}
	
	public MemberDto login(MemberDto mem) {
		return dao.login(mem);
	}
}

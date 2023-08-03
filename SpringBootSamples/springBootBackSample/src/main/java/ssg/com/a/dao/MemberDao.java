package ssg.com.a.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ssg.com.a.dto.MemberDto;

@Mapper
@Repository
public interface MemberDao {
	
	int idcheck(String id);
	
	int addMember(MemberDto mem);
	
	MemberDto login(MemberDto mem);
}

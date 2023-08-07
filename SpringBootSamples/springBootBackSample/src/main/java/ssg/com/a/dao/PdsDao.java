package ssg.com.a.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import ssg.com.a.dto.PdsDto;

@Mapper
@Repository
public interface PdsDao {

	List<PdsDto> pdslist();
	PdsDto pdsget(int seq);
	
	int pdswrite(PdsDto pds);
	int pdsupload(PdsDto pds);
	
	PdsDto pdsdetail(int seq);
	
	int pdsupdate(PdsDto dto);
}

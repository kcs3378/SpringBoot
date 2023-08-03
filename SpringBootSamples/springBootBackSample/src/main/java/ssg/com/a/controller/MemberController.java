package ssg.com.a.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import ssg.com.a.dto.MemberDto;
import ssg.com.a.service.MemberService;

@RestController
public class MemberController {
	
	@Autowired
	MemberService service;
	
	@PostMapping("idcheck")
	public String idcheck(String id) {
		System.out.println("MemberController idcheck(String id)" + new Date());
		
		int count = service.idcheck(id);
		if(count==0) {
			return "YES";
		}
		return "NO";
	}

	@PostMapping("addMember")
	public String addMember(MemberDto mem) {
		System.out.println("MemberController addMember" + new Date());
		int count = service.addMember(mem);
		System.out.println("count " + count);
		if(count>0) {
			return "YES";
		}
		return "NO";
	}
	
	@PostMapping("login")
	public MemberDto login(MemberDto mem) {
		System.out.println("MemberController login()" + new Date());

		MemberDto dto = service.login(mem);
		/* MemberDto 객체를 넘기는 방식
		if(dto != null) {
			return dto;
		}
		return null;
		*/
		
		// String을 넘기는 방식
		if (dto != null){
			System.out.println("in");
			return dto;
		}
		System.out.println( dto );
		// null로 들어가도 html javascript 부분에선 String ""으로 받음
		return dto;
	}
}

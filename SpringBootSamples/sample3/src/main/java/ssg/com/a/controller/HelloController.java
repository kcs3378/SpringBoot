package ssg.com.a.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import ssg.com.a.dto.MemberDto;
import ssg.com.a.service.MemberService;

@RestController
public class HelloController {

	@Autowired
	MemberService service;
	
	@GetMapping("allMember")
	public List<MemberDto> allMember(){
		System.out.println("HelloController allMember()" + new Date());
		
		return service.allMember();
	}
	
	@GetMapping("memberAll")
	public List<MemberDto> memberAll(String title, int number){
		System.out.println("HelloController memberAll()" + new Date());
		System.out.println("title: " + title);
		System.out.println("number: " + number);
		
		return service.allMember();
	}
	@PostMapping("All")
	public List<MemberDto> All(String title, int number){
		System.out.println("HelloController All()" + new Date());
		System.out.println("title: " + title);
		System.out.println("number: " + number);
		
		return service.allMember();
	}
}

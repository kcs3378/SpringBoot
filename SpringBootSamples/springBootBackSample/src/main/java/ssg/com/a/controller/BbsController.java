package ssg.com.a.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ssg.com.a.dto.BbsDto;
import ssg.com.a.dto.BbsParam;
import ssg.com.a.service.BbsService;

@RestController
public class BbsController {

	@Autowired
	BbsService service;
	
	@GetMapping("bbsList")
	public List<BbsDto> bbsList(BbsParam param){
		System.out.println("BbsController bbsList " + new Date());
		
		List<BbsDto> list = service.bbsList(param);
		
		return list;
	}
}

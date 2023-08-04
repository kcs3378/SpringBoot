package ssg.com.a.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ssg.com.a.dto.BbsDto;
import ssg.com.a.dto.BbsParam;
import ssg.com.a.service.BbsService;

@RestController
public class BbsController {

	@Autowired
	BbsService service;
	
	@GetMapping("bbsList")
	public Map<String, Object> bbsList(BbsParam param){
		System.out.println("BbsController bbsList " + new Date());
		
		List<BbsDto> list = service.bbsList(param);
		
		//글의 총 수
		int count = service.getAllBbs(param);
		int pageBbs = count / 10;
		if((count % 10) > 0) {
			pageBbs = pageBbs + 1;
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("bbsList", list);
		map.put("pageBbs", pageBbs);
		//map.put("pageNumber", param.getPageNumber());
		
		return map;
	}
	
	@PostMapping("bbsWriteAf")
	public String bbsWriteAf(BbsDto dto) {
		System.out.println("BbsController bbsWriteAf() " + new Date());
		
		int count = service.bbsWriteAf(dto);
		if(count > 0) {
			return "NO";
		}
		return "YES";
	}
	
	@GetMapping("bbsDetail")
	public BbsDto bbsDetail(int seq){
		System.out.println("BbsController bbsDetail() " + new Date());
		
		BbsDto dto = service.bbsDetail(seq);
		if(dto == null) {
			System.out.println("bbsDetail is " + dto);
			return dto;
		}
		
		return dto;
	}
	
	@PostMapping("bbsUpdate")
	public String bbsUpdate(BbsDto temp) {
		System.out.println("BbsController bbsUpdate() " + new Date());
		System.out.println(temp.getSeq());
		BbsDto dto = service.bbsDetail(temp.getSeq());
		if(dto == null) {
			System.out.println("bbsUpdate is " + dto);
			return "NO";
		}
		dto.setTitle(temp.getTitle());
		dto.setContent(temp.getContent());
		int count = service.bbsUpdate(dto);
		if(count <= 0) {
			
			return "NO";
		}
		return "YES";
		
	}
	
	@GetMapping("bbsAnswer")
	public String bbsAnswer(BbsDto bbs) {
		System.out.println("BbsController bbsanswer " + new Date());
		
		int count = service.bbsAnswer(bbs);
		if(count > 0) {
			return "YES";
		}
		return "NO";
	}
}



















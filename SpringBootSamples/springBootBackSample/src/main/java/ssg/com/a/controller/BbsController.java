package ssg.com.a.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ssg.com.a.dto.BbsComment;
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
		map.put("cnt", count); // react 중 pagination 사용시 활용
		return map;
	}
	
	@PostMapping("bbsWriteAf")
	public String bbsWriteAf(BbsDto dto) {
		System.out.println("BbsController bbsWriteAf() " + new Date());
		System.out.println(dto.toString());
		int count = service.bbsWriteAf(dto);
		System.out.println(count);
		if(count > 0) {
			return "YES";
		}
		return "NO";
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
	
	@PostMapping("bbsDelete")
	public String bbsDelete(BbsDto temp) {
		System.out.println("BbsController bbsUpdate() " + new Date());
		System.out.println(temp.getSeq());
		BbsDto dto = service.bbsDetail(temp.getSeq());
		if(dto == null) {
			System.out.println("bbsUpdate is " + dto);
			return "NO";
		}
		int count = service.bbsDelete(dto);
		if(count > 0) {
			
			return "YES";
		}
		return "NO";
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
/*
	@PostMapping("commentWrite.do")
	public String commentWrite(BbsComment dto, Model model) {
		System.out.println("BbsController commentWriteAf " + new Date());
		
		boolean isS = service.commentWrite(dto);
		String commentwrite = "COMMENT_ADD_OK";
		if(isS == false) {
			commentwrite = "COMMENT_ADD_NO";
		}
		model.addAttribute("commentwrite", commentwrite);
		
		return "message";
		
	}
	
	@ResponseBody
	@GetMapping("bbscommentList.do")
	public List<BbsComment> commentList(int seq, int pageNumber){
		System.out.println("BbsController commentList() "+ seq + " " + new Date());
		BbsParam param = new BbsParam(seq, pageNumber);
		List<BbsComment> temp = service.commentList(param);
		
		return temp;
	}
	
	
	
	@GetMapping("commentDelete.do")
	public String commentDelete(int post_num, int seq, int pageNumber, Model model) {
		System.out.println("BbsController commentDeleteAf() " + new Date());

		BbsComment temp = new BbsComment(post_num, seq);
		BbsComment comDto = service.commentGet(temp);

		boolean isS = service.commentDelete(comDto);

		String message = "COMMENTDELETE_YES";
		if(isS == false) {
			message = "COMMENTDELETE_NO";
		}
		model.addAttribute("commentDelete", message);
		model.addAttribute("pageNumber" + pageNumber);
		
		return "message";
		
	}
	@ResponseBody
	@PostMapping("commentAnswer.do")
	public String commentAnswer(int post_num, int seq, String content, String user_id, Model model) {
		System.out.println("BbsController commentAnswer() " + new Date());

		BbsComment temp = new BbsComment(post_num, seq, content, user_id);
		BbsComment comDto = service.commentGet(temp);	

		comDto.setContent(content);
		comDto.setUser_id(user_id);
		boolean isS = service.commentAnswer(comDto);
		String message = "COMMENTANSWER_YES";
		if(isS == false) {
			message = "COMMENTANSWER_NO";
		}
		
		model.addAttribute("commentAnswer", message);
		
		return "message";
	}
*/
}



















package ssg.com.a.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ssg.com.a.dto.BbsDto;
import ssg.com.a.dto.PdsDto;
import ssg.com.a.service.PdsService;
import util.DownloadView;
import util.MediaTypeUtiles;
import util.PdsUtil;

@RestController
public class PdsController {

	@Autowired
	PdsService service;
	
	@Autowired
	ServletContext servletContext;
	
	@GetMapping("pdslist")
	public List<PdsDto> pdslist(){
		System.out.println("PdsController pdslist " + new Date());
		
		List<PdsDto> list = service.pdslist();
		
		return list;
	}
	
	@PostMapping("pdswrite")
	public String pdswrite(PdsDto dto) {
		System.out.println("PdsController pdswrite() " + new Date());	
		
		int count = service.pdswrite(dto);
		if(count > 0) {
			return "YES";
		}
		return "NO";
	}
	
	@PostMapping("pdsupload")
	public String pdsupload(PdsDto pds,
		    @RequestParam("fileupload") MultipartFile fileupload,
							HttpServletRequest request) {
		System.out.println("PdsController pdsupload() " + new Date());
		
		// filename 취득
		String filename = fileupload.getOriginalFilename();
		System.out.println("filename:" + filename);
		
		//PdsDto pds = new PdsDto(id, title, content, filename);
		// db에 저장을 위해서 
		pds.setFilename(filename);
		
		// upload의 경로 설정
		// tomcat
		String path = request.getServletContext().getRealPath("/upload");		
		// 폴더
	//	String fupload = "d:\\tmp";
		
		System.out.println("파일업로드경로:" + path);
		
		// 파일명을 변경 data.txt -> 323423423.txt 
		String newfilename = PdsUtil.getNewfileName(filename);		
		System.out.println("newfilename:" + newfilename);
		
		// db에 저장을 위해서
		pds.setNewfilename(newfilename);
		
		// 파일 업로드
		File file = new File(path + "/" + newfilename);
		
		try {
			// 실제 파일에 업로드
			FileUtils.writeByteArrayToFile(file, fileupload.getBytes());
			
			// DB에 저장
			System.out.println(pds.toString());
			int count = service.pdsupload(pds);
			if(count > 0) {
				System.out.println("파일 업로드 성공!");
				return "YES";
			}
			System.out.println("파일 업로드 실패");
			return "NO";
			
		} catch (IOException e) {			 
			e.printStackTrace();
		}		
		
		return "NO";
	}
	
	@GetMapping("pdsdetail")
	public PdsDto pdsdetail(int seq){
		System.out.println("PdsController pdsdetail() " + new Date());
		
		PdsDto dto = service.pdsdetail(seq);
		if(dto == null) {
			System.out.println("pdsdetail is " + dto);
			return dto;
		}
		
		return dto;
	}
	
	@GetMapping("pdsdownload")
	public ResponseEntity<InputStreamResource> pdsdownload(int seq,
								 HttpServletRequest request) throws Exception {
		
		System.out.println("PdsController pdsdownload() " + new Date());
		
		PdsDto dto = service.pdsget(seq);
		
		// 경로
		String path = request.getServletContext().getRealPath("/upload");
		
//		String fupload = "d:\\tmp";
		
		// 파일의 타입을 조사
		MediaType mediaType = MediaTypeUtiles.getMediaTypeForFileName(servletContext, dto.getFilename());
		System.out.println("filename: " + dto.getFilename());
		System.out.println("mediaType: " + mediaType);
		
		// 다운로드를 받을 파일
		File file = new File(path + File.separator + dto.getNewfilename());
		
		InputStreamResource is = new InputStreamResource(new FileInputStream(file));
		
		String filename = URLEncoder.encode(dto.getFilename(), "utf-8");

		return ResponseEntity.ok()												//원본 파일명
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"" + filename + "\"")
				.contentType(mediaType)
				.contentLength(file.length()).body(is);	
	}
	
	@PostMapping("pdsupdate")
	public String pdsupdate(PdsDto temp, @RequestParam(value = "fileupload", required = false) MultipartFile fileupload,
			HttpServletRequest request) {
		System.out.println("PdsController pdsupdate() " + new Date());
		System.out.println(temp.getSeq());
		PdsDto dto = service.pdsdetail(temp.getSeq());
		
		if(fileupload != null && !fileupload.equals("")) {
			String filename = fileupload.getOriginalFilename();
			System.out.println("filename:" + filename);
			dto.setFilename(filename);
			String path = request.getServletContext().getRealPath("/upload");		
			
			System.out.println("파일업로드경로:" + path);
			
			// 기존의 파일을 삭제
			File df = new File(path + "/" + dto.getNewfilename());
			df.delete();
			
			String newfilename = PdsUtil.getNewfileName(filename);		
			System.out.println("newfilename:" + newfilename);
			
			dto.setNewfilename(newfilename);
			
			File file = new File(path + "/" + newfilename);
			
			try {
				FileUtils.writeByteArrayToFile(file, fileupload.getBytes());
				
			} catch (IOException e) {			 
				e.printStackTrace();
			}		
		}
		
		if(dto == null) {
			System.out.println("pdsupdate is " + dto);
			return "NO";
		}
		dto.setTitle(temp.getTitle());
		dto.setContent(temp.getContent());
		int count = service.pdsupdate(dto);
		if(count > 0) {
			
			return "YES";
		}
		return "NO";
		
	}
	
	@PostMapping("deletefile")
	public ResponseEntity<String> deletefile(int seq, HttpServletRequest request){
		PdsDto dto = service.pdsget(seq);
		
		String path = request.getServletContext().getRealPath("/upload");		
		
		System.out.println("파일업로드경로:" + path);
		
		File file = new File(path + "/" + dto.getNewfilename());
		file.delete();
		dto.setFilename("");
		dto.setNewfilename("");
		service.pdsupdate(dto);
		return new ResponseEntity<String>("YES",HttpStatus.OK);
	}
	
}

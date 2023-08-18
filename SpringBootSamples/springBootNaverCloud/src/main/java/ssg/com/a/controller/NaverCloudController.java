package ssg.com.a.controller;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;
import ssg.com.a.naver.NaverCloud;

@RestController
public class NaverCloudController {
	
	// 음성인식 wav -> String
	@PostMapping("/fileUpload")
	public String fileUpload(@RequestParam("uploadFile")MultipartFile uploadFile,
							HttpServletRequest request) throws IOException {
		System.out.println("NaverCloudController fileUpload" + new Date());
		
		// tomcat
		String uploadPath = request.getServletContext().getRealPath("/upload");
		
		// 파일명 취득
		String filename = uploadFile.getOriginalFilename();
		String filepath = uploadPath + File.separator + filename;
		
		System.out.println(filepath);
		
		//fileupload
		try {
		BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(new File(filepath)));
		os.write(uploadFile.getBytes());
		os.close();
		} catch (Exception e) {
			return "file load fail";
		}
		
		// Naver Cloud
		String response = NaverCloud.processSTT(filepath);
		
		return response;
	}

	@PostMapping("/fileUploadCFR")
	public String fileUploadCFR(@RequestParam("uploadFileCFR")MultipartFile uploadFile,
							@RequestParam("select")String select,
							HttpServletRequest request) throws IOException {
		System.out.println("NaverCloudController CFR" + new Date());
		
		// tomcat
		String uploadPath = request.getServletContext().getRealPath("/upload");
		
		// 파일명 취득
		String filename = uploadFile.getOriginalFilename();
		String filepath = uploadPath + File.separator + filename;
		
		//String celebrity = uploadFile.;
		
		System.out.println(filepath);
		
		//fileupload
		try {
		BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(new File(filepath)));
		os.write(uploadFile.getBytes());
		os.close();
		} catch (Exception e) {
			return "file load fail";
		}
		
		// Naver Cloud
		String response = NaverCloud.processCFR(filepath, select);//, celebrity
		
		return response;
	}
	
	@PostMapping("/tts")
	public String tts(@RequestParam("message") String message,
			@RequestParam("speaker") String speaker,
	                  HttpServletRequest request) {
	    System.out.println("NaverCloudController tts " + new Date());
	    System.out.println(message);
	    // tomcat
	    String uploadPath = request.getServletContext().getRealPath("/upload");
	    Map<String,String> msg = NaverCloud.processTTS(message, uploadPath, speaker);

	    // mp3 파일의 URL 생성
	    String audioURL = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/upload/" + msg.get("tempname") + ".mp3";
	    System.out.println(audioURL);
	    return audioURL;
	}

	@PostMapping("/ObjectDetection")
	public String ObjectDetection(@RequestParam("uploadfile")MultipartFile uploadFile,
									HttpServletRequest request) {
		System.out.println("NaverCloudController ObjectDetection " + new Date());
		System.out.println(uploadFile);
		
		// tomcat
	    String uploadPath = request.getServletContext().getRealPath("/upload");
		
	    // 파일명 취득
 		String filename = uploadFile.getOriginalFilename();
 		String filepath = uploadPath + File.separator + filename;
 		
 		System.out.println(filepath);
		
		//fileupload
		try {
		BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(new File(filepath)));
		os.write(uploadFile.getBytes());
		os.close();
		} catch (Exception e) {
			return "file load fail";
		}
		
		// Naver Cloud
		String response = NaverCloud.processObjectDetection(filepath);
		
		return response;
	}
}

















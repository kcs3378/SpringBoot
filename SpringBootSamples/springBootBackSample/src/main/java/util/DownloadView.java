package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.AbstractView;

import ssg.com.a.service.PdsService;

public class DownloadView extends AbstractView{

	@Autowired
	PdsService service;
	
	@ResponseBody
	@Override
	public void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("DownloadView renderMergedOutputModel");
		
		File downloadFile = (File)model.get("downloadFile");
		String filename = (String)model.get("filename");
		int seq = (Integer)model.get("seq");
		
		System.out.println("filename:" + filename);
		System.out.println("seq:" + seq);
		
		response.setContentType(this.getContentType());
		response.setContentLength((int)downloadFile.length());
		System.out.println("set");
		
		// 이 설정은 한글 파일의 경우에 적용된다.
		filename = URLEncoder.encode(filename, "utf-8");
		System.out.println("encode");
		
		// 다운로드를 받는 윈도우
		response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
		response.setHeader("Content-Transfer-Encoding", "binary;");
		response.setHeader("Content-Length", "" + downloadFile.length());
		response.setHeader("Pragma", "no-cache;"); 
		response.setHeader("Expires", "-1;");
		System.out.println("response");
		OutputStream os = response.getOutputStream();
		FileInputStream fis = new FileInputStream(downloadFile);
		System.out.println("upstream");
		// 실제로 데이터를 기입
		FileCopyUtils.copy(fis, os);
		System.out.println("copy");
		// download count 증가
		
		if(fis != null) {
			fis.close();
		}
		
	}

	
}




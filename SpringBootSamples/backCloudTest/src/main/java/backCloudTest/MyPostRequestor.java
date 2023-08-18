package backCloudTest;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.swing.plaf.synth.SynthFormattedTextFieldUI;

public class MyPostRequestor {

	public static void main(String[] args) throws Exception {
		
		DataOutputStream os = null;
		BufferedReader br = null;
		
		String apiUrl = "http://localhost:9025/CloudTest/index.jsp";
		
		URL url = new URL(apiUrl);
		
		// 웹페이지를 읽고 크롤링도 해온다
		HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		conn.setRequestMethod("POST");
		conn.setDoOutput(true);
		os = new DataOutputStream(conn.getOutputStream());
		
		String params = "name=" + "John" + "&address=" + "Seoul";
		os.writeBytes(params);
		
		// 접속 후에 데이터(json)를 받는다
		br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		
		String str, json="";
		int i = 0;
		while((str = br.readLine()) != null) {
			json += str;
		}
		
		System.out.println(json);
	}

}

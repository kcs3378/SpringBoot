package ssg.com.a.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ssg.com.a.dto.HumanDto;

//@Component
@RestController //Restful -> @Controller + @Responsebody << 합쳐놓은게 RestController임
public class HelloController {
	
	//@RequestMapping(value = "/", method = RequestMethod.GET)
	@GetMapping("/")
	public String hello() {
		System.out.println("HelloController hello() " + new Date());
		return "안녕하세요";
	}
	
	@GetMapping("/test")
	public int test() {
		System.out.println("HelloController test() " + new Date());
		return 1024;
	}
	
	@GetMapping("/human")
	public HumanDto human() {
		System.out.println("HelloController human() " + new Date());
		HumanDto human = new HumanDto(1, "홍길동", "부산시");
		return human;
	}
	
	@GetMapping("conn_param")
	public String conn_param(int num, String str) {
		System.out.println("HelloController conn_param(int num, String str) " + new Date());
		
		System.out.println("num:" + num + " str:" + str);
		return "success";
	}
	@GetMapping("param_obj")
	public String param_obj(HumanDto h) {
		System.out.println("HelloController param_obj(HumanDto h) " + new Date());
		
		System.out.println(h.toString());
		return "성공!";
	}
	@GetMapping("getlist")
	public List<HumanDto> getlist(){
		System.out.println("HelloController List<HumanDto> " + new Date());
		
		List<HumanDto> list = new ArrayList<HumanDto>();
		list.add(new HumanDto(1, "홍길동", "부산시"));
		list.add(new HumanDto(2, "성춘향", "남원시"));
		list.add(new HumanDto(3, "일지매", "전주시"));
		
		return list;
	}
	@GetMapping("getmap")
	public Map<String, Object> getmap(){//-> String title, List
		Map<String, Object> map = new HashMap<>();
		
		String title = "나는 성공한다";
		
		map.put("title", title);
		map.put("humanlist", getlist());
		
		return map;
	}
}

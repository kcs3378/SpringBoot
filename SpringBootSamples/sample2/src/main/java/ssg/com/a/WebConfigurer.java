package ssg.com.a;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// 접속허가 설정
@Configuration
public class WebConfigurer implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("*"); // << 개발할 때는 *를 사용해서 어떤 주소로도 접근 가능하게함
		//registry.addMapping("/**").allowedOrigins("http://localhost:9025");
	}

}

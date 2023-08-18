package ssg.com.a;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.stereotype.Component;

// 특수문자 허용
@Component
public class TomcatWebServerConfig implements WebServerFactoryCustomizer<TomcatServletWebServerFactory>{

	@Override
	public void customize(TomcatServletWebServerFactory factory) {
		factory.addConnectorCustomizers(connector -> connector.setProperty("relaxedQueryChars", "|{}[]"));
	}
}

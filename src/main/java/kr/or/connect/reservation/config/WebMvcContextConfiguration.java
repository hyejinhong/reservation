package kr.or.connect.reservation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration // 설정파일이라는 것 명시
@EnableWebMvc
@ComponentScan(basePackages = {"kr.or.connect.reservation.controller"})
@EnableSwagger2
@Import(SwaggerConfig.class)
public class WebMvcContextConfiguration extends WebMvcConfigurerAdapter {
	// url 요청에 따라 어떻게 처리할지 설정
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/css/**").addResourceLocations("/css/").setCachePeriod(31556926);
		registry.addResourceHandler("/img/**").addResourceLocations("/img/").setCachePeriod(31556926);
		registry.addResourceHandler("/js/**").addResourceLocations("/js/").setCachePeriod(31556926);
	
		// swagger 관련 리소스 등록
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
	
	// default servlet handler를 사용하게 한다.
	// 매핑정보가 없는 url이 요청되었을 때 Spring의 default servlet Http Reauest Handler가 처리하도록 함
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	
	// 특정 URL에 대한 처리를 컨트롤러 클래스를 작성하지 않고 매핑할 수 있또록 함
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		// /로 요청이 들어오면 index라는 view를 보여주세요.
		registry.addViewController("/").setViewName("index");
	}
	
	// view resolver가 뷰의 이름을 가지고 어떤 뷰인지 찾아내도록 함
	@Bean
	public InternalResourceViewResolver getInternalResourceViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");

		return resolver;
	}
	
	

}

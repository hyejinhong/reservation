package kr.or.connect.reservation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration // 설정파일이라는 것 명시
@EnableWebMvc
@ComponentScan(basePackages = {"kr.or.connect.reservation.controller"})

public class WebMvcContextConfiguration extends WebMvcConfigurerAdapter {
	// url 요청에 따라 어떻게 처리할지 설정
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/css/**").addResourceLocations("/css/").setCachePeriod(31556926);
		registry.addResourceHandler("/img/**").addResourceLocations("/img/").setCachePeriod(31556926);
		registry.addResourceHandler("/js/**").addResourceLocations("/js/").setCachePeriod(31556926);
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
		System.out.println("addViewContollers 호출");
		// /로 요청이 들어오면 index라는 view를 보여주세요.
		registry.addViewController("/").setViewName("index");
	}
	
	// view resolver가 뷰의 이름을 가지고 어떤 뷰인지 찾아내도록 함
	@Bean
	public InternalResourceViewResolver getInternalResourceViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		
		System.out.println(resolver.toString());
		return resolver;
	}
}

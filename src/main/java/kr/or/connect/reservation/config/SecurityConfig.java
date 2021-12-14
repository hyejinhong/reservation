package kr.or.connect.reservation.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

import kr.or.connect.reservation.service.security.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	CustomUserDetailsService customUserDetailsService;
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/webjars/**");
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailsService);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		CharacterEncodingFilter filter = new CharacterEncodingFilter();
		filter.setEncoding("UTF-8");
		filter.setForceEncoding(true);
		http.addFilterBefore(filter, CsrfFilter.class);
		
		http.csrf().disable()
		.authorizeRequests()
		.antMatchers("/", "/main", "/users/loginerror", "/users/joinform", "/users/join", "/users/welcome", "/users/join").permitAll() // /main은 누구나 가능
		.antMatchers("/api/**").hasRole("USER")
		.anyRequest().authenticated() // 그 외 요청은 인증 후 접근해야함
		.and()
			.formLogin()
			.loginPage("/users/loginform")
			// 로그인시 사용할 아이디, 비밀번호 설정
			// <input>의 name 파라미터와 같은 이름을 가져야 한다.
			.usernameParameter("userId")
			.passwordParameter("password")
			
			.loginProcessingUrl("/authenticate")
			.failureForwardUrl("/users/loginerror?login_error=1")
			.defaultSuccessUrl("/", true)
			.permitAll()
		.and()
			.logout()
			.logoutUrl("/logout") // 로그아웃 url 설정
			.logoutSuccessUrl("/");
	}
	
	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
}

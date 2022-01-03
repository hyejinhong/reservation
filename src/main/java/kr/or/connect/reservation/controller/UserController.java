package kr.or.connect.reservation.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/users")
public class UserController {
	
	@Autowired
	BCryptPasswordEncoder encoder;
	
	@GetMapping("/loginform")
	public String loginform() {
		return "/users/loginform";
	}
}

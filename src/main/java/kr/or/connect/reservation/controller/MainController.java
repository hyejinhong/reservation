package kr.or.connect.reservation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

	@GetMapping(path = "/test")
	public String loginform() {
		return "/users/loginform";
	}
}

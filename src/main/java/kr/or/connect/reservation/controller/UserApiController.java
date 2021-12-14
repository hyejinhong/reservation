package kr.or.connect.reservation.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/api/users")
public class UserApiController {

	@GetMapping
	public String test() {
		return "hi";
	}
}

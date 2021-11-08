package kr.or.connect.reservation.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import kr.or.connect.reservation.dto.Category;
import kr.or.connect.reservation.service.CategoryService;

@RestController
@RequestMapping(path="/api/categories")
public class CategoryApiController {
	@Autowired
	CategoryService categoryService;
	
	@ApiOperation(value = "카테고리 목록")
	@ApiResponses({
		@ApiResponse(code=200, message="OK"),
		@ApiResponse(code=500, message="Exception")
	})
	@GetMapping
	public Map<String, Object> getCategories() {
		// 목록 가져오기
		List<Category> categories = categoryService.getCategories();
		
		// 반환할 객체 만들기
		Map<String, Object> map = new HashMap<>();
		map.put("size", categories.size());
		map.put("items", categories);
		
		return map;
	}

}

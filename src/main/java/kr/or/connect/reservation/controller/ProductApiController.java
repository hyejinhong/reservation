package kr.or.connect.reservation.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import kr.or.connect.reservation.dto.Product;
import kr.or.connect.reservation.service.ProductImageService;
import kr.or.connect.reservation.service.ProductPriceService;
import kr.or.connect.reservation.service.ProductService;

@RestController
@RequestMapping(path="/api/products")
public class ProductApiController {
	@Autowired
	ProductService productService;
	
	@Autowired
	ProductImageService productImageService;
	
	@Autowired
	ProductPriceService productPriceService;
	
	
	@ApiOperation(value = "상품 목록")
	@ApiResponses({
		@ApiResponse(code=200, message="OK"),
		@ApiResponse(code=500, message="Exception")
	})
	@GetMapping
	public Map<String, Object> getProducts(
			@RequestParam(defaultValue="0") int categoryId, @RequestParam(defaultValue="0") int start) {
		
		// 목록 가져오기
		List<Product> products = productService.getProduct(categoryId, start);
		int totalCount = productService.getTotalCount(categoryId);
		
		// 반환할 객체
		Map<String, Object> map = new HashMap<>();
		map.put("products", products);
		map.put("totalCount", totalCount);
		map.put("productCount", products.size()); // 읽어온 전시 상품 수

		return map;
	}
}

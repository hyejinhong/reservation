package kr.or.connect.reservation.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.reservation.dto.Category;
import kr.or.connect.reservation.dto.DisplayInfo;
import kr.or.connect.reservation.dto.Product;
import kr.or.connect.reservation.dto.Promotion;
import kr.or.connect.reservation.dto.ReservationUserComment;
import kr.or.connect.reservation.service.CategoryService;
import kr.or.connect.reservation.service.CommentService;
import kr.or.connect.reservation.service.DisplayInfoService;
import kr.or.connect.reservation.service.ProductService;
import kr.or.connect.reservation.service.PromotionService;

@RestController
@RequestMapping(path="/api")
public class ReservationApiController {
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	DisplayInfoService displayInfoService;
	
	@Autowired
	PromotionService promotionService;
	
	@Autowired
	CommentService commentService;
	
	@Autowired
	ProductService productService;
	
	@GetMapping(path="/categories")
	public Map<String, Object> getCategories() {
		// 목록 가져오기
		List<Category> categories = categoryService.getCategories();
		int size = categoryService.getSize();
		
		// 반환할 객체 만들기
		Map<String, Object> map = new HashMap<>();
		map.put("size", size);
		map.put("items", categories);
		
		return map;
	}
	
	@GetMapping(path="/displayinfos")
	public Map<String, Object> getDisplayInfos(
			@RequestParam(name="categoryId", required=false, defaultValue="0") int categoryId,
			@RequestParam(name="start", required=false, defaultValue="0") int start) {
		
		// 목록 가져오기
		List<DisplayInfo> displayInfos = displayInfoService.getDisplayInfos(categoryId);
		
		// 반환할 객체 만들기
		Map<String, Object> map = new HashMap<>();
		map.put("totalCount", displayInfoService.getTotalCount(categoryId));
		map.put("productCount", displayInfoService.getProductCount(categoryId));
		map.put("products", displayInfos);
		
		return map;
	}

//	@GetMapping(path="/displayinfos/{displayId}")
//	public Map<String, Object> getDisplayInfo() {
//		// 반환할 객체 만들기
//		Map<String, Object> map = new HashMap<>();
//		
//	}
	
	
	@GetMapping(path="/promotions")
	public Map<String, Object> getPromotions() {
		// 목록 가져오기
		List<Promotion> promotions = promotionService.getPromotions();
		int size = promotionService.getSize();
		
		// 반환할 객체 만들기
		Map<String, Object> map = new HashMap<>();
		map.put("size", size);
		map.put("items", promotions);
		
		return map;
	}
	
	@GetMapping(path="/comments")
	public Map<String, Object> getComments(
			@RequestParam(name="productId", required=false, defaultValue="0") int productId,
			@RequestParam(name="start", required=false, defaultValue="0") int start) {
		
		// 목록 가져오기
		List<ReservationUserComment> comments = commentService.getComments(productId);
		int totalCount = commentService.getTotalCount(productId, start);
		int commentCount = commentService.getCommentCount(productId, start);
		
		// 반환할 객체 만들기
		Map<String, Object> map = new HashMap<>();
		map.put("reservationUserComments", comments);
		map.put("totalCount", totalCount);
		map.put("commentCount", commentCount);
		
		
		return map;
	}

	@GetMapping(path="/products")
	public Map<String, Object> getProducts(
			@RequestParam(name="categoryId", required=false, defaultValue="0") int categoryId,
			@RequestParam(name="start", required=false, defaultValue="0") int start) {
		
		// 목록 가져오기
		List<Product> products = productService.getProducts(categoryId);
		int totalCount = productService.getTotalCount(categoryId);
		
		// 반환할 객체
		Map<String, Object> map = new HashMap<>();
		map.put("products", products);
		map.put("totalCount", totalCount);
		map.put("productCount", products.size()); // 읽어온 전시 상품 수

		return map;
	}
}

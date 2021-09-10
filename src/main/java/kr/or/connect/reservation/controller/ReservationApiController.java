package kr.or.connect.reservation.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import kr.or.connect.reservation.dto.Category;
import kr.or.connect.reservation.dto.DisplayInfo;
import kr.or.connect.reservation.dto.DisplayInfoImage;
import kr.or.connect.reservation.dto.Product;
import kr.or.connect.reservation.dto.ProductImage;
import kr.or.connect.reservation.dto.ProductPrice;
import kr.or.connect.reservation.dto.Promotion;
import kr.or.connect.reservation.dto.ReservationUserComment;
import kr.or.connect.reservation.service.CategoryService;
import kr.or.connect.reservation.service.CommentService;
import kr.or.connect.reservation.service.DisplayInfoImageService;
import kr.or.connect.reservation.service.DisplayInfoService;
import kr.or.connect.reservation.service.ProductImageService;
import kr.or.connect.reservation.service.ProductPriceService;
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
	
	@Autowired
	ProductImageService productImageService;
	
	@Autowired
	DisplayInfoImageService displayInfoImageService;
	
	@Autowired
	ProductPriceService productPriceService;
	
	@ApiOperation(value = "카테고리 목록")
	@ApiResponses({
		@ApiResponse(code=200, message="OK"),
		@ApiResponse(code=500, message="Exception")
	})
	@GetMapping(path="/categories")
	public Map<String, Object> getCategories() {
		// 목록 가져오기
		List<Category> categories = categoryService.getCategories();
		
		// 반환할 객체 만들기
		Map<String, Object> map = new HashMap<>();
		map.put("size", categories.size());
		map.put("items", categories);
		
		return map;
	}
	
	
	@ApiOperation(value = "전시정보 목록")
	@ApiResponses({
		@ApiResponse(code=200, message="OK"),
		@ApiResponse(code=500, message="Exception")
	})
	@GetMapping(path="/displayinfos")
	public Map<String, Object> getDisplayInfos(
			@RequestParam(name="categoryId", required=false, defaultValue="0") int categoryId,
			@RequestParam(name="start", required=false, defaultValue="0") int start) {
		
		// 목록 가져오기
		List<DisplayInfo> displayInfos = displayInfoService.listDisplayInfo(categoryId, start);
		
		// 반환할 객체 만들기
		Map<String, Object> map = new HashMap<>();
		map.put("totalCount", displayInfoService.getTotalCount(categoryId));
		map.put("productCount", displayInfos.size());
		map.put("products", displayInfos);
		
		return map;
	}
	
	
	@ApiOperation(value = "전시정보 상세보기")
	@ApiResponses({
		@ApiResponse(code=200, message="OK"),
		@ApiResponse(code=500, message="Exception")
	})
	@GetMapping(path="/displayinfos/{displayId}")
	public Map<String, Object> getDisplayInfo(@PathVariable("displayId") int displayInfoId) {
		// 데이터 가져오기
		Product product = productService.getByDisplayInfoId(displayInfoId);
		List<ProductImage> productImages = productImageService.getProductImages(product.getId());
		List<DisplayInfoImage> displayInfoImages = displayInfoImageService.getDisplayInfoImages(displayInfoId);
		int avgScore = displayInfoService.getAvgScore(displayInfoId);
		List<ProductPrice> productPrices = productPriceService.getProductPrice(product.getId());
		
		// 반환할 객체 만들기
		Map<String, Object> map = new HashMap<>();
		map.put("product", product);
		map.put("product_images", productImages);
		map.put("display_info_images", displayInfoImages);
		map.put("product_prices", productPrices);
		map.put("avgScore", avgScore);

		return map;
	}
	
	
	
	@ApiOperation(value = "프로모션 목록")
	@ApiResponses({
		@ApiResponse(code=200, message="OK"),
		@ApiResponse(code=500, message="Exception")
	})
	@GetMapping(path="/promotions")
	public Map<String, Object> getPromotions() {
		// 데이터 가져오기
		List<Promotion> promotions = promotionService.getPromotions();
		
		// 반환할 객체 만들기
		Map<String, Object> map = new HashMap<>();
		map.put("size", promotions.size());
		map.put("items", promotions);
		
		return map;
	}
	
	
	@ApiOperation(value = "댓글 목록")
	@ApiResponses({
		@ApiResponse(code=200, message="OK"),
		@ApiResponse(code=500, message="Exception")
	})
	@GetMapping(path="/comments")
	public Map<String, Object> getComments(
			@RequestParam(name="productId", required=false, defaultValue="0") int productId,
			@RequestParam(name="start", required=false, defaultValue="0") int start) {
		
		// 목록 가져오기
		List<ReservationUserComment> comments = commentService.getComments(productId, start);
		int totalCount = commentService.getTotalCount(productId);
		
		// 반환할 객체 만들기
		Map<String, Object> map = new HashMap<>();
		map.put("reservationUserComments", comments);
		map.put("totalCount", totalCount);
		map.put("commentCount", comments.size());
		
		return map;
	}

	
	@ApiOperation(value = "상품 목록")
	@ApiResponses({
		@ApiResponse(code=200, message="OK"),
		@ApiResponse(code=500, message="Exception")
	})
	@GetMapping(path="/products")
	public Map<String, Object> getProducts(
			@RequestParam(name="categoryId", required=false, defaultValue="0") int categoryId,
			@RequestParam(name="start", required=false, defaultValue="0") int start) {
		
		// 목록 가져오기
		List<Product> products = productService.listProduct(categoryId, start);
		int totalCount = productService.getTotalCount(categoryId);
		
		// 반환할 객체
		Map<String, Object> map = new HashMap<>();
		map.put("products", products);
		map.put("totalCount", totalCount);
		map.put("productCount", products.size()); // 읽어온 전시 상품 수

		return map;
	}
}

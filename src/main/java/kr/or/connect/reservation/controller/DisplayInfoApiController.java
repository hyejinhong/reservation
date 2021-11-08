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

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import kr.or.connect.reservation.dto.DisplayInfo;
import kr.or.connect.reservation.dto.DisplayInfoImage;
import kr.or.connect.reservation.dto.Product;
import kr.or.connect.reservation.dto.ProductImage;
import kr.or.connect.reservation.dto.ProductPrice;
import kr.or.connect.reservation.service.DisplayInfoImageService;
import kr.or.connect.reservation.service.DisplayInfoService;
import kr.or.connect.reservation.service.ProductImageService;
import kr.or.connect.reservation.service.ProductPriceService;
import kr.or.connect.reservation.service.ProductService;

@RestController
@RequestMapping(path="/api/displayinfos")
public class DisplayInfoApiController {
	@Autowired
	DisplayInfoService displayInfoService;
	
	@Autowired
	DisplayInfoImageService displayInfoImageService;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	ProductImageService productImageService;

	@Autowired
	ProductPriceService productPriceService;

	@ApiOperation(value = "전시정보 목록")
	@ApiResponses({
		@ApiResponse(code=200, message="OK"),
		@ApiResponse(code=500, message="Exception")
	})
	@GetMapping
	public Map<String, Object> getDisplayInfos(
			@RequestParam(defaultValue="0") int categoryId, @RequestParam(defaultValue="0") int start) {
		
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
	@GetMapping(path="/{displayId}")
	public Map<String, Object> getDisplayInfo(@PathVariable int displayInfoId) {
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
	
}

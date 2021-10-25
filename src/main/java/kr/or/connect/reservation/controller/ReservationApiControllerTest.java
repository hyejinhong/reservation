package kr.or.connect.reservation.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Tag;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import kr.or.connect.reservation.config.ApplicationConfig;
import kr.or.connect.reservation.config.WebMvcContextConfiguration;
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


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebMvcContextConfiguration.class, ApplicationConfig.class})
public class ReservationApiControllerTest {

	@InjectMocks
	public ReservationApiController reservationApiController;
	
	@Mock
	CategoryService categoryService;
	@Mock
	CommentService commentService;
	@Mock
	DisplayInfoImageService displayInfoImageService;
	@Mock
	DisplayInfoService displayInfoService;
	@Mock
	ProductImageService productImageService;
	@Mock
	ProductService productService;
	@Mock
	ProductPriceService productPriceService;
	@Mock
	PromotionService promotionService;
	
	private MockMvc mockMvc;
	
	// 테스트 메소드 실행 전 @Before 메소드가 먼저 실행됨.
	@Before
	public void createController() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(reservationApiController).build();
	}
	
	@Test
	@Tag("카테고리")
	public void getCategories() throws Exception {
		Category category = new Category();
		category.setCount(555);
		category.setId(1);
		category.setName("Jazz");
		
		List<Category> list = Arrays.asList(category);
		when(categoryService.getCategories()).thenReturn(list);
		
		RequestBuilder reqBuilder = MockMvcRequestBuilders.get("/api/categories").contentType(MediaType.APPLICATION_JSON);
		mockMvc.perform(reqBuilder).andExpect(status().isOk()).andDo(print());
		
		verify(categoryService).getCategories();
	}
	
	@Test
	@Tag("상품")
	public void getProducts() throws Exception {
		Product product = new Product();
		product.setCategoryId(0);
		product.setContent("태민 콘서트입니다.");
		product.setDescription("T1011101");
		product.setDisplayInfoId(1);
		product.setId(0);
		product.setName("K-POP 콘서트");

		List<Product> list = Arrays.asList(product);
		when(productService.listProduct(0, 0)).thenReturn(list);
		
		RequestBuilder reqBuilder = MockMvcRequestBuilders.get("/api/products").contentType(MediaType.APPLICATION_JSON);
		mockMvc.perform(reqBuilder).andExpect(status().isOk()).andDo(print());
		
		verify(productService).listProduct(0, 0);
	}
	
	@Test
	@Tag("프로모션")
	public void getPromotions() throws Exception {
		Promotion promotion = new Promotion();
		promotion.setCategoryId(0);
		promotion.setCategoryName("콘서트");
		promotion.setDescription("키 솔로 콘서트");
		promotion.setId(0);
		promotion.setProductId(0);

		List<Promotion> list = Arrays.asList(promotion);
		when(promotionService.getPromotions()).thenReturn(list);
		
		
		RequestBuilder reqBuilder = MockMvcRequestBuilders.get("/api/promotions").contentType(MediaType.APPLICATION_JSON);
		mockMvc.perform(reqBuilder).andExpect(status().isOk()).andDo(print());
		
		verify(promotionService).getPromotions();
	}
	
	@Test
	@Tag("상품 이미지")
	public void getProductImages() throws Exception {
		ProductImage productImage = new ProductImage();
		productImage.setContentType("얼음같던 사랑");
		productImage.setProductImageId(0);
		productImage.setType("image");
		productImage.setProductId(0);
		List<ProductImage> list = Arrays.asList(productImage);
		
		Product product = new Product();
		product.setCategoryId(0);
		product.setContent("태민 콘서트입니다.");
		product.setDescription("T1011101");
		product.setDisplayInfoId(1);
		product.setId(0);
		product.setName("K-POP 콘서트");

		
		when(productImageService.getProductImages(0)).thenReturn(list);
		when(productService.getByDisplayInfoId(0)).thenReturn(product);
		when(productPriceService.getProductPrice(0)).thenReturn(null);
		
		RequestBuilder reqBuilder = MockMvcRequestBuilders.get("/api/displayinfos/{displayId}", "0").contentType(MediaType.APPLICATION_JSON);
		mockMvc.perform(reqBuilder).andExpect(status().isOk()).andDo(print());
		
		verify(productImageService).getProductImages(0);
	}
	
	@Test
	@Tag("전시 이미지")
	public void getDisplayInfoImages() throws Exception {
		DisplayInfoImage image = new DisplayInfoImage();
		image.setId(0);
		
		Product product = new Product();
		product.setCategoryId(0);
		product.setContent("태민 콘서트입니다.");
		product.setDescription("T1011101");
		product.setDisplayInfoId(1);
		product.setId(0);
		product.setName("K-POP 콘서트");

		when(productService.getByDisplayInfoId(0)).thenReturn(product);

		List<DisplayInfoImage> list = Arrays.asList(image);		
		when(displayInfoImageService.getDisplayInfoImages(0)).thenReturn(list);
		
		RequestBuilder reqBuilder = MockMvcRequestBuilders.get("/api/displayinfos/{displayId}", "0").contentType(MediaType.APPLICATION_JSON);
		mockMvc.perform(reqBuilder).andExpect(status().isOk()).andDo(print());
		
		verify(displayInfoImageService).getDisplayInfoImages(0);
	}
	
	@Test
	@Tag("상품 가격")
	public void getProductPrices() throws Exception {
		ProductPrice productPrice = new ProductPrice();
		productPrice.setId(0);
		List<ProductPrice> list = Arrays.asList(productPrice);
		
		Product product = new Product();
		product.setCategoryId(0);
		product.setContent("태민 콘서트입니다.");
		product.setDescription("T1011101");
		product.setDisplayInfoId(1);
		product.setId(0);
		product.setName("K-POP 콘서트");
		
		when(productPriceService.getProductPrice(0)).thenReturn(list);
		when(productService.getByDisplayInfoId(0)).thenReturn(product);
		
		RequestBuilder reqBuilder = MockMvcRequestBuilders.get("/api/displayinfos/{displayId}", "0").contentType(MediaType.APPLICATION_JSON);
		mockMvc.perform(reqBuilder).andExpect(status().isOk()).andDo(print());
		
		verify(productPriceService).getProductPrice(0);
	}
	
	@Test
	@Tag("댓글")
	public void getComments() throws Exception {
		ReservationUserComment comment = new ReservationUserComment();
		comment.setId(0);
		List<ReservationUserComment> list = Arrays.asList(comment);
		
		when(commentService.getComments(0, 0)).thenReturn(list);
		
		RequestBuilder reqBuilder = MockMvcRequestBuilders.get("/api/comments").contentType(MediaType.APPLICATION_JSON);
		mockMvc.perform(reqBuilder).andExpect(status().isOk()).andDo(print());
		
		verify(commentService).getComments(0, 0);
	}
}

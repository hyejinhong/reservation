package kr.or.connect.reservation.controller;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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
import kr.or.connect.reservation.dto.Promotion;
import kr.or.connect.reservation.service.CategoryService;
import kr.or.connect.reservation.service.CommentService;
import kr.or.connect.reservation.service.DisplayInfoImageService;
import kr.or.connect.reservation.service.DisplayInfoService;
import kr.or.connect.reservation.service.ProductImageService;
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
	DisplayInfoImageService displayImageService;
	@Mock
	DisplayInfoService displayInfoService;
	@Mock
	ProductImageService productImageService;
	@Mock
	ProductService productService;
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
}

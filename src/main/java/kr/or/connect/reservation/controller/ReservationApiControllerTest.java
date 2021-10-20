package kr.or.connect.reservation.controller;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import kr.or.connect.reservation.config.ApplicationConfig;
import kr.or.connect.reservation.config.WebMvcContextConfiguration;
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
	
	@Before
	public void createController() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(reservationApiController).build();
	}
	
}

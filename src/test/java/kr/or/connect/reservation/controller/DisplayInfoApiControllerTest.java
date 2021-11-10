package kr.or.connect.reservation.controller;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import kr.or.connect.reservation.config.ApplicationConfig;
import kr.or.connect.reservation.config.WebMvcContextConfiguration;
import kr.or.connect.reservation.dto.DisplayInfoImage;
import kr.or.connect.reservation.dto.Product;
import kr.or.connect.reservation.service.DisplayInfoImageService;
import kr.or.connect.reservation.service.DisplayInfoService;
import kr.or.connect.reservation.service.ProductImageService;
import kr.or.connect.reservation.service.ProductPriceService;
import kr.or.connect.reservation.service.ProductService;
import kr.or.connect.reservation.service.PromotionService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebMvcContextConfiguration.class, ApplicationConfig.class})
public class DisplayInfoApiControllerTest {
	
	@InjectMocks
	DisplayInfoApiController controller;
	@Mock
	DisplayInfoImageService imageService;
	@Mock
	DisplayInfoService service;
	@Mock
	ProductImageService productImageService;
	@Mock
	ProductService productService;
	@Mock
	ProductPriceService productPriceService;
	@Mock
	PromotionService promotionService;

	private MockMvc mockMvc;
	
	@Before
	public void createController() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}
	
	@Test
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
		when(imageService.getDisplayInfoImages(0)).thenReturn(list);
		
		RequestBuilder reqBuilder = MockMvcRequestBuilders.get("/api/displayinfos/{displayId}", "0").contentType(MediaType.APPLICATION_JSON);
		mockMvc.perform(reqBuilder).andExpect(status().isOk()).andDo(print());
		
		verify(imageService).getDisplayInfoImages(0);
	}
}

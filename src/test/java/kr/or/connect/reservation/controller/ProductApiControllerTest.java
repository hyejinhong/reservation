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
import kr.or.connect.reservation.dto.Product;
import kr.or.connect.reservation.dto.ProductImage;
import kr.or.connect.reservation.dto.ProductPrice;
import kr.or.connect.reservation.service.ProductImageService;
import kr.or.connect.reservation.service.ProductPriceService;
import kr.or.connect.reservation.service.ProductService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebMvcContextConfiguration.class, ApplicationConfig.class})

public class ProductApiControllerTest {
	
	@InjectMocks
	ProductApiController controller;
	
	@Mock
	ProductImageService imageService;
	@Mock
	ProductService service;
	@Mock
	ProductPriceService priceService;
	private MockMvc mockMvc;
	
	@Before
	public void createController() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}
	
	@Test
	public void getProducts() throws Exception {
		Product product = new Product();
		product.setCategoryId(0);
		product.setContent("태민 콘서트입니다.");
		product.setDescription("T1011101");
		product.setDisplayInfoId(1);
		product.setId(0);
		product.setName("K-POP 콘서트");

		List<Product> list = Arrays.asList(product);
		when(service.getProduct(0, 0)).thenReturn(list);
		
		RequestBuilder reqBuilder = MockMvcRequestBuilders.get("/api/products").contentType(MediaType.APPLICATION_JSON);
		mockMvc.perform(reqBuilder).andExpect(status().isOk()).andDo(print());
		
		verify(service).getProduct(0, 0);
	}
	
	@Test
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
		
		when(priceService.getProductPrice(0)).thenReturn(list);
		when(service.getByDisplayInfoId(0)).thenReturn(product);
		
		RequestBuilder reqBuilder = MockMvcRequestBuilders.get("/api/displayinfos/{displayId}", "0").contentType(MediaType.APPLICATION_JSON);
		mockMvc.perform(reqBuilder).andExpect(status().isOk()).andDo(print());
		
		verify(priceService).getProductPrice(0);
	}
	
	@Test
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

		
		when(imageService.getProductImages(0)).thenReturn(list);
		when(service.getByDisplayInfoId(0)).thenReturn(product);
		when(priceService.getProductPrice(0)).thenReturn(null);
		
		RequestBuilder reqBuilder = MockMvcRequestBuilders.get("/api/displayinfos/{displayId}", "0").contentType(MediaType.APPLICATION_JSON);
		mockMvc.perform(reqBuilder).andExpect(status().isOk()).andDo(print());
		
		verify(imageService).getProductImages(0);
	}
}

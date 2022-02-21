package kr.or.connect.reservation.controller;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.security.Principal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatcher;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.connect.reservation.config.ApplicationConfig;
import kr.or.connect.reservation.config.SecurityConfig;
import kr.or.connect.reservation.config.WebMvcContextConfiguration;
import kr.or.connect.reservation.dto.ReservationInfo;
import kr.or.connect.reservation.dto.ReservationInfoPrice;
import kr.or.connect.reservation.dto.ReservationInfoResult;
import kr.or.connect.reservation.dto.User;
import kr.or.connect.reservation.service.ReservationInfoPriceService;
import kr.or.connect.reservation.service.ReservationInfoService;
import kr.or.connect.reservation.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { WebMvcContextConfiguration.class, ApplicationConfig.class, SecurityConfig.class })
public class ReservationApiControllerTest {

	@InjectMocks
	ReservationInfoApiController controller;

	@Mock
	ReservationInfoService infoService;
	@Mock
	ReservationInfoPriceService priceService;
	@Mock
	UserService userService;
	
	private MockMvc mockMvc;

	@Before
	@WithAnonymousUser
	public void createController() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void write() throws Exception {
		Map<String, Object> input = new HashMap<>();
		ObjectMapper objectMapper = new ObjectMapper();

		input.put("prices", "[{\"count\": 2, \"productPriceId\": 3}]");
		input.put("productId", 1);
		input.put("displayInfoId", 1);
		input.put("reservationYearMonthDay", "2008.05.25");
		input.put("userId", 525);
		System.out.println(input);
		
		RequestBuilder reqBuilder = MockMvcRequestBuilders.post("/api/reservationInfos")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(input));
		mockMvc.perform(reqBuilder).andExpect(status().isOk()).andDo(print());
		
		ReservationInfo reservationInfo = new ReservationInfo(1, 1, 525, "2008.05.25");
		reservationInfo.setId(0);
		System.out.println(reservationInfo.toString());
		verify(infoService).addReservationInfo(Matchers.same(reservationInfo));
	}
	
	@Test
	public void getReservationInfosByUser() throws Exception {
		// User
		User user = new User();
		user.setId(525);
		user.setEmail("dontcallme@connect.co.kr");
		user.setPassword("1234");
		user.setName("전화하지마");
		when(userService.getUserByEmail("dontcallme@connect.co.kr")).thenReturn(user);
		
		// ReservationInfo
		ReservationInfo reservationInfo = new ReservationInfo();
		reservationInfo.setId(123);
		reservationInfo.setProductId(12);
		reservationInfo.setUserId(525);
		
		// Principal
		Principal mockPrincipal = Mockito.mock(Principal.class);
		when(mockPrincipal.getName()).thenReturn("dontcallme@connect.co.kr");
		
		List<ReservationInfo> list = Arrays.asList(reservationInfo);
		when(infoService.getReservationInfosByUser(525)).thenReturn(list);
		
		RequestBuilder reqBuilder = MockMvcRequestBuilders.get("/api/reservationInfos").contentType(MediaType.APPLICATION_JSON)
				.principal(mockPrincipal);
		mockMvc.perform(reqBuilder).andExpect(status().isOk()).andDo(print());
		
		verify(infoService).getReservationInfosByUser(525);
	}

}

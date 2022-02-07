package kr.or.connect.reservation.controller;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
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

import kr.or.connect.reservation.config.ApplicationConfig;
import kr.or.connect.reservation.config.SecurityConfig;
import kr.or.connect.reservation.config.WebMvcContextConfiguration;
import kr.or.connect.reservation.dto.ReservationInfo;
import kr.or.connect.reservation.dto.ReservationInfoResult;
import kr.or.connect.reservation.dto.User;
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
	UserService userService;

	private MockMvc mockMvc;

	@Before
	@WithAnonymousUser
	public void createController() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
//	@WithMockUser(username = "dontcallme@email.com", password="1234", roles = {"USER"})
	public void getReservationInfos() throws Exception {
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

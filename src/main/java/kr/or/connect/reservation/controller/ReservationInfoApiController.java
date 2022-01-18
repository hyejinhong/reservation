package kr.or.connect.reservation.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.connect.reservation.dto.ReservationInfo;
import kr.or.connect.reservation.dto.ReservationInfoPrice;
import kr.or.connect.reservation.dto.ReservationInfoResult;
import kr.or.connect.reservation.dto.User;
import kr.or.connect.reservation.service.ReservationInfoPriceService;
import kr.or.connect.reservation.service.ReservationInfoService;
import kr.or.connect.reservation.service.UserService;

@RestController
@RequestMapping(path = "/api/reservationInfos")
public class ReservationInfoApiController {

	@Autowired
	ReservationInfoService reservationInfoService;
	@Autowired
	ReservationInfoPriceService reservationInfoPriceService;
	@Autowired
	UserService userService;
	
	@PostMapping
	public ReservationInfoResult write(@RequestBody Map<String, Object> body) throws Exception {
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		String pricesString = body.get("prices").toString().replace("=", ":");
		pricesString = pricesString.substring(1, pricesString.length()-1);
		
		try {
			// Price 파싱
			Map<String, Integer> prices = mapper.readValue(pricesString, Map.class);

			// Info 저장			

			int productId = (int) body.get("productId");
			int displayInfoId  = (int) body.get("displayInfoId");
			String reservationYearMonthDay = (String) body.get("reservationYearMonthDay");
			int userId = (int) body.get("userId");

			ReservationInfo reservationInfo = new ReservationInfo(productId, displayInfoId, userId, reservationYearMonthDay);
			// 생성된 reservationInfo id
			int generatedId = reservationInfoService.addReservationInfo(reservationInfo);
			
			// price 정보 추가
			ReservationInfoPrice reservationInfoPrice = new ReservationInfoPrice(generatedId, prices.get("productPriceId"), prices.get("count"));
			reservationInfoPriceService.addReservationInfoPrices(reservationInfoPrice);

			reservationInfo.setId(generatedId);

			// 일단 하드코딩으로라도.
//			result.put("id", reservationInfo.getId());
//			result.put("productId", reservationInfo.getProductId());
//			result.put("cancelFlag", reservationInfo.getCancelFlag());
//			result.put("displayInfoId", reservationInfo.getDisplayInfoId());
//			result.put("userId", reservationInfo.getUserId());
//			result.put("reservationDate", reservationInfo.getReservationDate());
//			result.put("createDate", reservationInfo.getCreateDate());
//			result.put("modifyDate", reservationInfo.getModifyDate());
//			result.put("prices", body.get("prices"));
			
			return reservationInfoService.getReservationInfoResult(generatedId);
		
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		return null;
	}
	
	@GetMapping
	public Map<String, Object> getReservationInfosByUser(Principal principal) {
		Map<String, Object> result = new HashMap<>();
		
		// 현재 로그인한 사람 userId 찾기
		User user = userService.getUserByEmail(principal.getName());
		
		// ReservationInfos 가져와야함		
		List<ReservationInfo> list = reservationInfoService.getReservationInfosByUser(user.getId());
		result.put("size", list.size());
		result.put("items", list);
		return result;
	}
	
	@PutMapping
	public Map<String, Object> updateReservation(@RequestBody Map<String, Object> body) {
		int id = (int) body.get("id");
		String result = reservationInfoService.updateReservation(id);
		
		Map<String, Object> ret = new HashMap<>();
		ret.put("result", result);
		return ret;
	}
}

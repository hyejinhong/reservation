package kr.or.connect.reservation.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.connect.reservation.dto.ReservationInfo;
import kr.or.connect.reservation.service.ReservationInfoService;

@RestController
@RequestMapping(path = "/api/reservationInfos")
public class ReservationInfoApiController {

	@Autowired
	ReservationInfoService reservationInfoService;
	
	@PostMapping
	public ReservationInfo write(@RequestBody Map<String, Object> body) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		String pricesString = body.get("prices").toString().replace("=", ":");
		pricesString = pricesString.substring(1, pricesString.length()-1);
		
		Map<String, Object> result = new HashMap<>();

		try {
			Map<String, Integer> prices = mapper.readValue(pricesString, Map.class);
			
			int productId = (int) body.get("productId");
			int displayInfoId  = (int) body.get("displayInfoId");
			String reservationYearMonthDay = (String) body.get("reservationYearMonthDay");
			int userId = (int) body.get("userId");
			
			int generatedId = reservationInfoService.addReservationInfo(prices, productId, displayInfoId, reservationYearMonthDay, userId);
			
			ReservationInfo reservationInfo = reservationInfoService.getReservationInfo(generatedId);

			return reservationInfo;
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		// 여기 해야됨 prices 추가
		return null;
	}
	
}

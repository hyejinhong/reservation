package kr.or.connect.reservation.service;

import java.util.Map;

public interface ReservationInfoService {
	int addReservationInfo(Map<String, Integer> prices, int productId, int displayInfoId, String reservationYearMonthDay, int userId) throws Exception;
}

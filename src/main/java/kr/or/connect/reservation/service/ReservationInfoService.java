package kr.or.connect.reservation.service;

import java.util.List;
import java.util.Map;

import kr.or.connect.reservation.dto.ReservationInfo;

public interface ReservationInfoService {
	int addReservationInfo(Map<String, Integer> prices, int productId, int displayInfoId, String reservationYearMonthDay, int userId) throws Exception;
	ReservationInfo getReservationInfo(int id);
	List<ReservationInfo> getReservationInfosByUser(int userId);
}

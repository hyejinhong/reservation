package kr.or.connect.reservation.service;

import java.util.List;
import java.util.Map;

import kr.or.connect.reservation.dto.ReservationInfo;

public interface ReservationInfoService {
	int addReservationInfo(ReservationInfo reservationInfo);
	ReservationInfo getReservationInfo(int id);
	List<ReservationInfo> getReservationInfosByUser(int userId);
	String updateReservation(int id);
}

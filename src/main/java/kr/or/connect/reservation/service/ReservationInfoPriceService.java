package kr.or.connect.reservation.service;

import java.util.List;

import kr.or.connect.reservation.dto.ReservationInfoPrice;

public interface ReservationInfoPriceService {
	int addReservationInfoPrices(ReservationInfoPrice reservationInfoPrice);
	List<ReservationInfoPrice> getReservationInfoPrices(int reservationInfoId);
}

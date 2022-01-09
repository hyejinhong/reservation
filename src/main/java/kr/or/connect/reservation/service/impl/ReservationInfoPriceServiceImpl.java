package kr.or.connect.reservation.service.impl;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.connect.reservation.dao.ReservationInfoPriceDao;
import kr.or.connect.reservation.dto.ReservationInfo;
import kr.or.connect.reservation.dto.ReservationInfoPrice;
import kr.or.connect.reservation.service.ReservationInfoPriceService;

@Service
public class ReservationInfoPriceServiceImpl implements ReservationInfoPriceService {

	@Autowired
	ReservationInfoPriceDao reservationInfoPriceDao;
	
	@Override
	public List<ReservationInfoPrice> getReservationInfoPrices(int reservationInfoId) {
		return reservationInfoPriceDao.getPrices(reservationInfoId);
	}

	@Override
	public int addReservationInfoPrices(ReservationInfoPrice reservationInfoPrice) {
		return reservationInfoPriceDao.insert(reservationInfoPrice);
	}

}

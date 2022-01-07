package kr.or.connect.reservation.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.connect.reservation.dao.ReservationInfoDao;
import kr.or.connect.reservation.dto.ReservationInfo;
import kr.or.connect.reservation.service.ReservationInfoService;

@Service
public class ReservationInfoServiceImpl implements ReservationInfoService{
	
	@Autowired
	ReservationInfoDao reservationInfoDao;
	
	@Override
	public int addReservationInfo(Map<String, Integer> prices, int productId, int displayInfoId, String reservationYearMonthDay, int userId) throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("yyyy.mm.dd");
		ReservationInfo reservationInfo = new ReservationInfo(productId, displayInfoId, userId, format.parse(reservationYearMonthDay));
		int id = reservationInfoDao.insert(reservationInfo);
		
		return id;
	}

	@Override
	public ReservationInfo getReservationInfo(int id) {
		return reservationInfoDao.findById(id);
	}

}

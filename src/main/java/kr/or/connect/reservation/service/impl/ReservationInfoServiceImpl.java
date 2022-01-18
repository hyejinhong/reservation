package kr.or.connect.reservation.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.connect.reservation.dao.ReservationInfoDao;
import kr.or.connect.reservation.dao.ReservationInfoPriceDao;
import kr.or.connect.reservation.dto.ReservationInfo;
import kr.or.connect.reservation.dto.ReservationInfoPrice;
import kr.or.connect.reservation.dto.ReservationInfoResult;
import kr.or.connect.reservation.service.ReservationInfoService;

@Service
public class ReservationInfoServiceImpl implements ReservationInfoService {
	
	@Autowired
	ReservationInfoDao reservationInfoDao;
	
	@Autowired
	ReservationInfoPriceDao priceDao;
	
	@Override
	public int addReservationInfo(ReservationInfo reservationInfo) {
		return reservationInfoDao.insert(reservationInfo); 
	}


	@Override
	public ReservationInfo getReservationInfo(int id) {
		ReservationInfo reservationInfo = reservationInfoDao.findById(id);
		
//		// price 찾아서 넣어주기
//		List<ReservationInfoPrice> prices = priceDao.getPrices(id);
//		reservationInfo.setPrices(prices);
		
		return reservationInfo;
	}

	@Override
	public List<ReservationInfo> getReservationInfosByUser(int userId) {
		ArrayList<ReservationInfo> infos = (ArrayList<ReservationInfo>) reservationInfoDao.findByUserId(userId);
		for(ReservationInfo info : infos) {
			
//			// price 찾아서 넣어주기
//			List<ReservationInfoPrice> prices = priceDao.getPrices(info.getId());
//			info.setPrices(prices);
		}
		
		return infos;
	}


	@Override
	public String updateReservation(int id) {
		return reservationInfoDao.update(id);
	}


	@Override
	public ReservationInfoResult getReservationInfoResult(int id) {
		ReservationInfoResult result = reservationInfoDao.findResultById(id);
		
		// price 찾아서 넣어주기
		List<ReservationInfoPrice> prices = priceDao.getPrices(id);
		result.setPrices(prices);
		
		return result;
	}
}

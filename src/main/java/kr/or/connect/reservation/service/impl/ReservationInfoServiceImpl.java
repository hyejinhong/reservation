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
	
	/*
	 * 아래처럼 ReservationInfoServiceImpl 안에 ReservationInfoPriceDao를 가져와서 쓰는 것이 어색한(?) 일이 아닌지 궁금합니다.
	 * ReservationInfoPriceDao는 ReservationInfoPriceService에서 호출하는 것이 좀 더 좋은 코드인가요??
	 * 만약 그렇다면, Info에 sumPrice를 set해주는 과정을 Controller에서 해야할 것 같은데 이것은 또 좋은 코드가 아니라고 생각되어서 궁금합니다..
	 */
	
	@Autowired
	ReservationInfoPriceDao priceDao;
	
	@Override
	public int addReservationInfo(ReservationInfo reservationInfo) {
		return reservationInfoDao.insert(reservationInfo); 
	}


	@Override
	public ReservationInfo getReservationInfo(int id) {
		ReservationInfo reservationInfo = reservationInfoDao.findById(id);
		
		// sumPrice
		int sumPrice = priceDao.getSumPrice(id);
		reservationInfo.setSumPrice(sumPrice);
		
		return reservationInfo;
	}

	@Override
	public List<ReservationInfo> getReservationInfosByUser(int userId) {
		ArrayList<ReservationInfo> infos = (ArrayList<ReservationInfo>) reservationInfoDao.findByUserId(userId);
		for(ReservationInfo info : infos) {
			
			// sumPrice
			int sumPrice = priceDao.getSumPrice(info.getId());
			info.setSumPrice(sumPrice);
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

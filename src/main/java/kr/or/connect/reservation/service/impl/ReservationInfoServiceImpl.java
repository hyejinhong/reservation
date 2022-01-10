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
import kr.or.connect.reservation.service.ReservationInfoService;

@Service
public class ReservationInfoServiceImpl implements ReservationInfoService {
	
	@Autowired
	ReservationInfoDao reservationInfoDao;
	
	@Autowired
	ReservationInfoPriceDao priceDao;
	
	@Override
	public int addReservationInfo(Map<String, Integer> prices, int productId, int displayInfoId, String reservationYearMonthDay, int userId) throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("yyyy.mm.dd");
		ReservationInfo reservationInfo = new ReservationInfo(productId, displayInfoId, userId, format.parse(reservationYearMonthDay));
		int id = reservationInfoDao.insert(reservationInfo);
		
		return id;
	}

	@Override
	public ReservationInfo getReservationInfo(int id) {
		ReservationInfo reservationInfo = reservationInfoDao.findById(id);
		
		// price 찾아서 넣어주기
		List<ReservationInfoPrice> prices = priceDao.getPrices(id);
		reservationInfo.setPrices(prices);
		
		return reservationInfo;
	}

	@Override
	public List<ReservationInfo> getReservationInfosByUser(int userId) {
		ArrayList<ReservationInfo> infos = (ArrayList<ReservationInfo>) reservationInfoDao.findByUserId(userId);
		for(ReservationInfo info : infos) {
			
			// price 찾아서 넣어주기
			List<ReservationInfoPrice> prices = priceDao.getPrices(info.getId());
			info.setPrices(prices);
		}
		
		return infos;
	}

}

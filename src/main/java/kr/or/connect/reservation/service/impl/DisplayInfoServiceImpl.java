package kr.or.connect.reservation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.reservation.dao.DisplayInfoDao;
import kr.or.connect.reservation.dto.DisplayInfo;
import kr.or.connect.reservation.service.DisplayInfoService;

@Service
public class DisplayInfoServiceImpl implements DisplayInfoService {

	@Autowired
	DisplayInfoDao displayInfoDao;
	
	// 전시 정보 조회
	public List<DisplayInfo> listDisplayInfo(Integer categoryId, Integer start) {
		return displayInfoDao.findByCategoryId(categoryId, start);
	}
	
	@Override
	public int removeDisplayInfo(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public DisplayInfo addDiaplayInfo(DisplayInfo displayInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	// 전체 전시 개수 반환
	@Override
	public int getTotalCount(Integer categoryId) {
		return displayInfoDao.getTotalCount(categoryId);
	}

	@Override
	public int getProductCount(Integer categoryId, Integer start) {
		return displayInfoDao.getProductCount(categoryId, start);
	}

	@Override
	public int getAvgScore(int displayInfoId) {
		return displayInfoDao.getAvgScore(displayInfoId);
	}

}

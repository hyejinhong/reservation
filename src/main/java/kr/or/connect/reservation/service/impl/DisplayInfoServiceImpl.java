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
	public List<DisplayInfo> getDisplayInfo(Integer categoryId, Integer start) {
		// 카테고리 정보 N
		if(categoryId == 0) {
			return displayInfoDao.findAll(start);
		}
		
		// 카테고리 정보 Y
		else {
			return displayInfoDao.findByCategoryId(categoryId, start);
		}
	}
	
	// 전체 전시 개수 반환
	@Override
	public int getTotalCount(Integer categoryId) {
		// 카테고리 정보 N
		if(categoryId == 0) {
			return displayInfoDao.getTotalCount();
		}
		
		// 카테고리 정보 Y
		else {
			return displayInfoDao.getTotalCountByCategoryId(categoryId);
		}	
	}

	@Override
	public int getAvgScore(int displayInfoId) {
		return displayInfoDao.getAvgScore(displayInfoId);
	}

}

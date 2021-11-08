package kr.or.connect.reservation.service;

import java.util.List;

import kr.or.connect.reservation.dto.DisplayInfo;

public interface DisplayInfoService {
	List<DisplayInfo> listDisplayInfo(Integer categoryId, Integer start);
	int getTotalCount(Integer categoryId);
	int getAvgScore(int displayInfoId);
}

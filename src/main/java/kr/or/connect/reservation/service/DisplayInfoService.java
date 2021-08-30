package kr.or.connect.reservation.service;

import java.util.List;

import kr.or.connect.reservation.dto.DisplayInfo;

public interface DisplayInfoService {
	public List<DisplayInfo> listDisplayInfo(Integer categoryId, Integer start);
	public int removeDisplayInfo(Integer id);
	public DisplayInfo addDiaplayInfo(DisplayInfo displayInfo);
	public int getTotalCount(Integer categoryId);
	public int getProductCount(Integer categoryId, Integer start);
	public int getAvgScore(int displayInfoId);
}

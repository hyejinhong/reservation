package kr.or.connect.reservation.service;

import java.util.List;

import kr.or.connect.reservation.dto.ProductPrice;

public interface ProductPriceService {
	public List<ProductPrice> getProductPrice(Integer productId);
}

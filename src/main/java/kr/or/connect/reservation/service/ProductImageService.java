package kr.or.connect.reservation.service;

import java.util.List;

import kr.or.connect.reservation.dto.ProductImage;

public interface ProductImageService {
	public List<ProductImage> getProductImages(Integer productId);
}

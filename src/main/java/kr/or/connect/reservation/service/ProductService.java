package kr.or.connect.reservation.service;

import java.util.List;

import kr.or.connect.reservation.dto.Product;

public interface ProductService {
	List<Product> listProduct(Integer categoryId, Integer start);
	Product getByDisplayInfoId(Integer displayInfoId);
	Integer getTotalCount(Integer categoryId);
}


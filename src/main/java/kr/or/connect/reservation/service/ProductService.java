package kr.or.connect.reservation.service;

import java.util.List;

import kr.or.connect.reservation.dto.Product;

public interface ProductService {
	public List<Product> listProduct(Integer categoryId, Integer start);
	public Product getByDisplayInfoId(Integer displayInfoId);
	public Integer getTotalCount(Integer categoryId);
}


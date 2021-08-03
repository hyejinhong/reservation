package kr.or.connect.reservation.service;

import java.util.List;

import kr.or.connect.reservation.dto.Product;

public interface ProductService {
	public List<Product> getProducts();
	public Integer getTotalCount();
	public Integer productCount();
}


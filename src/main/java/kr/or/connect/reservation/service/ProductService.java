package kr.or.connect.reservation.service;

import java.util.List;

import kr.or.connect.reservation.dto.Product;

public interface ProductService {
	public List<Product> listProduct(Integer categoryId);
	public List<Product> listProductByDisplayInfoId(Integer displayInfoId);
	public Integer getTotalCount(Integer categoryId);
	public Integer getProductCount();
}


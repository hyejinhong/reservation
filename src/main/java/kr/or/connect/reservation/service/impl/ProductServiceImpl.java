package kr.or.connect.reservation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.connect.reservation.dao.ProductDao;
import kr.or.connect.reservation.dto.Product;
import kr.or.connect.reservation.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	ProductDao productDao;
	
	@Override
	public List<Product> getProducts(Integer categoryId) {
		return productDao.getProducts(categoryId);
	}

	@Override
	public Integer getTotalCount(Integer categoryId) {
		return productDao.getTotalCount(categoryId);
	}

	@Override
	public Integer productCount() {
		// TODO Auto-generated method stub
		return null;
	}

}
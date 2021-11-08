package kr.or.connect.reservation.service.impl;

import static kr.or.connect.reservation.dao.sqls.ProductDaoSqls.SELECT_ALL_PRODUCT;
import static kr.or.connect.reservation.dao.sqls.ProductDaoSqls.SELECT_BY_CATEGORY_ID;

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
	public List<Product> getProduct(Integer categoryId, Integer start) {
		// 전체 카테고리 조회
		if(categoryId == 0) {
			return productDao.findAll(start);
		}
		else {
			return productDao.findByCategoryId(categoryId, start);
		}
	}
	
	@Override
	public Product getByDisplayInfoId(Integer displayInfoId) {
		return productDao.findByDisplayInfoId(displayInfoId);
	}

	@Override
	public Integer getTotalCount(Integer categoryId) {
		// 전체 카테고리 조회
		if(categoryId == 0) {
			return productDao.getTotalCount();
		}
		else {
			return productDao.getTotalCountByCategoryId(categoryId);
		}

		
	}

}

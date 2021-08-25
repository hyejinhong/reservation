package kr.or.connect.reservation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.connect.reservation.dao.ProductPriceDao;
import kr.or.connect.reservation.dto.ProductPrice;
import kr.or.connect.reservation.service.ProductPriceService;

@Service
public class ProductPriceServiceImpl implements ProductPriceService {
	@Autowired
	ProductPriceDao productPriceDao;
	
	@Override
	public List<ProductPrice> getProductPrice(Integer productId) {
		return productPriceDao.listByProductId(productId);
	}

}

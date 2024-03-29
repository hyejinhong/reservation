package kr.or.connect.reservation.dao;

import static kr.or.connect.reservation.dao.sqls.ProductImageDaoSqls.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservation.dto.Product;
import kr.or.connect.reservation.dto.ProductImage;

@Repository
public class ProductImageDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<ProductImage> rowMapper = BeanPropertyRowMapper.newInstance(ProductImage.class);
	
	public ProductImageDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<ProductImage> listByProductId(Integer productId) {
		Map<String, Integer> params = new HashMap<>();
		params.put("product_id", productId);
		
		return jdbc.query(SELECT_BY_PRODUCT_ID, params, rowMapper);
	}

}

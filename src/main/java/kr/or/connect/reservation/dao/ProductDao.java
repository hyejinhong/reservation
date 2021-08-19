package kr.or.connect.reservation.dao;

import static kr.or.connect.reservation.dao.ProductDaoSqls.*;

import java.util.Collections;
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

@Repository
public class ProductDao {
	private NamedParameterJdbcTemplate jdbc;
	private SimpleJdbcInsert insertAction;
	private RowMapper<Product> rowMapper = BeanPropertyRowMapper.newInstance(Product.class);
	
	public ProductDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
		this.insertAction = new SimpleJdbcInsert(dataSource)
				.withTableName("product")
				.usingGeneratedKeyColumns("id");
	}
	
	// 카테고리 ID별 상품 가져오기
	public List<Product> findByCategoryId(Integer categoryId) {
		// 카테고리 아이디 0이면 전체 조회
		if(categoryId == 0) {
			return jdbc.query(SELECT_ALL_PRODUCT, rowMapper);
		}
		else {
			Map<String, Integer> params = new HashMap<>();
			params.put("category_id", categoryId);
			
			return jdbc.query(SELECT_PRODUCT_BY_CATEGORY_ID, params, rowMapper);
		}
	}
	
	// total count 가져오기
	public Integer getTotalCount(Integer categoryId) {
		// 카테고리 아이디 0이면 전체 조회
		if(categoryId == 0) {
			return jdbc.queryForObject(SELECT_TOTAL_COUNT_ALL_PRODUCT, Collections.emptyMap(), Integer.class);
		}
		else {
			Map<String, Object> params = new HashMap<>();
			params.put("category_id", categoryId);
			
			return jdbc.queryForObject(SELECT_TOTAL_COUNT_BY_CATEGORY_ID, params, Integer.class);
		}
	}
}

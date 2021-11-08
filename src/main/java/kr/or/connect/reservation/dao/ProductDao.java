package kr.or.connect.reservation.dao;

import static kr.or.connect.reservation.dao.sqls.ProductDaoSqls.*;

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
	private RowMapper<Product> rowMapper = BeanPropertyRowMapper.newInstance(Product.class);
	
	public ProductDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}
	
	// 카테고리 ID별 상품 가져오기
	public List<Product> findByCategoryId(Integer categoryId, Integer start) {
		Map<String, Integer> params = new HashMap<>();
		params.put("category_id", categoryId);
		params.put("start", start);
		
		// 전체 카테고리 조회
		if(categoryId == 0) {
			return jdbc.query(SELECT_ALL_PRODUCT, params, rowMapper);
		}
		else {
			return jdbc.query(SELECT_BY_CATEGORY_ID, params, rowMapper);
		}
	}
	
	public Integer getTotalCount(Integer categoryId) {
		// 전체 카테고리 조회
		if(categoryId == 0) {
			return jdbc.queryForObject(SELECT_TOTAL_COUNT_ALL_CATEGORY, Collections.emptyMap(), Integer.class);
		}
		else {
			Map<String, Object> params = new HashMap<>();
			params.put("category_id", categoryId);
			
			return jdbc.queryForObject(SELECT_TOTAL_COUNT, params, Integer.class);
		}
	}

	public Product findByDisplayInfoId(Integer displayInfoId) {
		Map<String, Object> params = new HashMap<>();
		params.put("display_info_id", displayInfoId);
		
		return jdbc.queryForObject(SELECT_BY_DISPLAY_INFO_ID, params, rowMapper);
	}
}

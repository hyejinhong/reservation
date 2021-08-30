package kr.or.connect.reservation.dao;

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
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.reservation.dto.DisplayInfo;

import static kr.or.connect.reservation.dao.DisplayInfoDaoSqls.*;

@Repository
public class DisplayInfoDao {
	private NamedParameterJdbcTemplate jdbc;
	private SimpleJdbcInsert insertAction;
	private RowMapper<DisplayInfo> rowMapper = BeanPropertyRowMapper.newInstance(DisplayInfo.class);

	public DisplayInfoDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
		this.insertAction = new SimpleJdbcInsert(dataSource).withTableName("display_info")
				.usingGeneratedKeyColumns("id");
	}

	public DisplayInfo get(Integer displayInfoId) {
		Map<String, Object> params = new HashMap<>();
		params.put(SELECT_ONE, displayInfoId);
		
		return jdbc.queryForObject(SELECT_ONE, params, rowMapper);
	}

	// category_id 별 조회 결과 가져오기
	public List<DisplayInfo> findByCategoryId(Integer categoryId, Integer start) {

		// 카테고리 N, 시작 위치 N
		if (categoryId == 0 && start == -1) {
			return jdbc.query(SELECT_ALL, rowMapper);
		}
		
		// 카테고리 N, 시작 위치 Y
		else if(categoryId == 0 && start != -1) {
			Map<String, Integer> params = new HashMap<>();
			params.put("start", start);
			
			return jdbc.query(SELECT_ALL_CATEGORY_AND_START, params, rowMapper);
		}
		
		// 카테고리 Y, 시작 위치 N
		else if(categoryId != 0 && start == -1) {
			Map<String, Integer> params = new HashMap<>();
			params.put("category_id", categoryId);

			return jdbc.query(SELECT_BY_CATEGORY_ID, params, rowMapper);
		}
		
		// 카테고리 Y, 시작 위치 Y
		
		else {
			Map<String, Integer> params = new HashMap<>();
			params.put("category_id", categoryId);
			params.put("start", start);
			
			return jdbc.query(SELECT_BY_CATEGORY_ID_AND_START, params, rowMapper);
		}
	}

	// 해당 카테고리의 전시 상품 수
	public Integer getTotalCount(Integer categoryId) {

		// 전체 카테고리 조회
		if (categoryId == 0) {
			return jdbc.queryForObject(SELECT_TOTAL_COUNT_ALL_CATEGORY, Collections.emptyMap(), Integer.class);
		}
		else {
			Map<String, Integer> params = new HashMap<>();
			params.put("category_id", categoryId);

			return jdbc.queryForObject(SELECT_TOTAL_COUNT, params, Integer.class);
		}
	}

	// 읽어온 전시 상품 수
	public Integer getProductCount(Integer categoryId, Integer start) {
		
		// 카테고리 N, 시작 위치 N
		if (categoryId == 0 && start == -1) {
			return jdbc.queryForObject(SELECT_PRODUCT_COUNT_ALL_CATEGORY, Collections.emptyMap(), Integer.class);
		}
		
		// 카테고리 N, 시작 위치 Y
		else if(categoryId == 0 && start != -1) {
			Map<String, Integer> params = new HashMap<>();
			params.put("start", start);
			
			return jdbc.queryForObject(SELECT_PRODUCT_COUNT_ALL_CATEGORY_AND_START, params, Integer.class);
		}
		
		// 카테고리 Y, 시작 위치 N
		else if(categoryId != 0 && start == -1) {
			Map<String, Integer> params = new HashMap<>();
			params.put("category_id", categoryId);
			
			return jdbc.queryForObject(SELECT_PRODUCT_COUNT_BY_CATEGORY_ID, params, Integer.class);
		}
		
		// 카테고리 Y, 시작 위치 Y
		else {
			Map<String, Integer> params = new HashMap<>();
			params.put("category_id", categoryId);
			params.put("start", start);
			
			return jdbc.queryForObject(SELECT_PRODUCT_COUNT_BY_CATEGORY_ID_AND_START, params, Integer.class);
		}
		
		
		
	}

	public int getAvgScore(int displayInfoId) {
		Map<String, Integer> params = new HashMap<>();
		params.put("display_info_id", displayInfoId);
		return jdbc.queryForObject(SELECT_AVG_SCORE, params, Integer.class);
	}


}

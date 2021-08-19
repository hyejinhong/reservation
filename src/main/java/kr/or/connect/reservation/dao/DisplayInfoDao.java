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
	// 모든 전시 정보 가져오기
//	public List<DisplayInfo> selectAll() {
//		return jdbc.query(SELECT_ALL, rowMapper);
//	}

	// category_id 별 조회 결과 가져오기
	public List<DisplayInfo> findByCategoryId(Integer categoryId) {

		// 카테고리 아이디 0이면 전체 조회
		if (categoryId == 0) {
			return jdbc.query(SELECT_ALL, rowMapper);
		} else {
			Map<String, Integer> params = new HashMap<>();
			params.put("category_id", categoryId);

//			return jdbc.query(SELECT_BY_CATEGORY_ID, params, rowMapper);
			return jdbc.query(SELECT_BY_CATEGORY_ID, params, rowMapper);
		}
	}

	// get Total Count
	public Integer getTotalCount(Integer categoryId) {

		// 카테고리 아이디 0이면 전체 조회
		if (categoryId == 0) {
			return jdbc.queryForObject(SELECT_TOTAL_COUNT_ALL_CATEGORY, Collections.emptyMap(), Integer.class);
		} else {
			Map<String, Integer> params = new HashMap<>();
			params.put("category_id", categoryId);

			return jdbc.queryForObject(SELECT_TOTAL_COUNT, params, Integer.class);
		}
	}

	// get 카테고리별 Count
	public Integer getProductCount(Integer categoryId) {
		// 카테고리 아이디 0이면 전체 조회
		if (categoryId == 0) {
			return jdbc.queryForObject(SELECT_PRODUCT_COUNT_ALL_CATEGORY, Collections.emptyMap(), Integer.class);
		} else {
			Map<String, Integer> params = new HashMap<>();
			params.put("category_id", categoryId);
			return jdbc.queryForObject(SELECT_PRODUCT_COUNT, params, Integer.class);
		}
	}

	public int getAvgScore(int displayInfoId) {
		Map<String, Integer> params = new HashMap<>();
		params.put("display_info_id", displayInfoId);
		return jdbc.queryForObject(SELECT_AVG_SCORE, params, Integer.class);
	}


}

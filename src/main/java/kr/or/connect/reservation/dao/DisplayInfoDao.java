package kr.or.connect.reservation.dao;

import static kr.or.connect.reservation.dao.sqls.DisplayInfoDaoSqls.*;

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

@Repository
public class DisplayInfoDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<DisplayInfo> rowMapper = BeanPropertyRowMapper.newInstance(DisplayInfo.class);

	public DisplayInfoDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public DisplayInfo get(Integer displayInfoId) {
		Map<String, Object> params = new HashMap<>();
		params.put(SELECT_ONE, displayInfoId);
		
		return jdbc.queryForObject(SELECT_ONE, params, rowMapper);
	}

	// category_id 별 조회 결과 가져오기
	public List<DisplayInfo> findByCategoryId(Integer categoryId, Integer start) {
		Map<String, Integer> params = new HashMap<>();
		params.put("category_id", categoryId);
		params.put("start", start);
		
		// 카테고리 N
		if (categoryId == 0) {
			return jdbc.query(SELECT_ALL, params, rowMapper);
		}
		
		// 카테고리 Y
		else {
			return jdbc.query(SELECT_BY_CATEGORY_ID, params, rowMapper);
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

	public int getAvgScore(int displayInfoId) {
		Map<String, Integer> params = new HashMap<>();
		params.put("display_info_id", displayInfoId);
		return jdbc.queryForObject(SELECT_AVG_SCORE, params, Integer.class);
	}

}

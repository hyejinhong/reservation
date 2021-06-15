package kr.or.connect.reservation.dao;

import static kr.or.connect.reservation.dao.CommentDaoSqls.*;

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

import kr.or.connect.reservation.dto.ReservationUserComment;

@Repository
public class CommentDao {
	private NamedParameterJdbcTemplate jdbc;
	private SimpleJdbcInsert insertAction;
	private RowMapper<ReservationUserComment> rowMapper = BeanPropertyRowMapper.newInstance(ReservationUserComment.class);

	public CommentDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
		this.insertAction = new SimpleJdbcInsert(dataSource).withTableName("reservation_user_comment")
				.usingGeneratedKeyColumns("id");
	}

	// product_id 별 조회 결과 가져오기
	public List<ReservationUserComment> selectByProductId(Integer productId) {
		// 상품 id 없으면 전체 조회
		if (productId == 0) {
			return jdbc.query(SELECT_COMMENT_ALL, rowMapper);
		} 
		else {
			Map<String, Integer> params = new HashMap<>();
			params.put("product_id", productId);
			
			return jdbc.query(SELECT_COMMENT_BY_PRODUCT_ID, params, rowMapper);
		}
	}

	// get Total Count
	public Integer selectTotalCount(Integer productId, Integer start) {

		// 상품 id 없으면 전체 조회
		if (productId == 0) {
			Map<String, Integer> params = new HashMap<>();
			params.put("start", start);

//			return jdbc.queryForObject(SELECT_TOTAL_COUNT_ALL_PRODUCT, Collections.emptyMap(), Integer.class);
			return jdbc.queryForObject(SELECT_TOTAL_COUNT_ALL_PRODUCT, params, Integer.class);
		} 
		else {
			Map<String, Integer> params = new HashMap<>();
			params.put("product_id", productId);
			params.put("start", start);
			
			return jdbc.queryForObject(SELECT_TOTAL_COUNT_BY_PRODUCT_ID, params, Integer.class);
		}
	}

	public Integer selectCommentCount(Integer productId, Integer start) {
		// 상품 id 없으면 전체 조회
		if (productId == 0) {
			Map<String, Integer> params = new HashMap<>();
			params.put("start", start);

//			return jdbc.queryForObject(SELECT_TOTAL_COUNT_ALL_PRODUCT, Collections.emptyMap(), Integer.class);
			return jdbc.queryForObject(SELECT_TOTAL_COUNT_ALL_PRODUCT, params, Integer.class);
		} 
		else {
			System.out.println("4");
			Map<String, Integer> params = new HashMap<>();
			params.put("product_id", productId);
			params.put("start", start);
			
			return jdbc.queryForObject(SELECT_TOTAL_COUNT_BY_PRODUCT_ID, params, Integer.class);
		}
	}

}

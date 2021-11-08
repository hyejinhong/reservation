package kr.or.connect.reservation.dao;

import static kr.or.connect.reservation.dao.sqls.CommentDaoSqls.*;

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
import kr.or.connect.reservation.dto.ReservationUserCommentImage;

@Repository
public class CommentDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<ReservationUserComment> rowMapper = BeanPropertyRowMapper.newInstance(ReservationUserComment.class);
	private RowMapper<ReservationUserCommentImage> imageRowMapper = BeanPropertyRowMapper.newInstance(ReservationUserCommentImage.class);
	
	public CommentDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<ReservationUserComment> findAll(Integer start) {
		Map<String, Integer> params = new HashMap<>();
		params.put("start", start);
		
		List<ReservationUserComment> comments = jdbc.query(SELECT_COMMENT_ALL, params, rowMapper);
		return comments;
	}
	
	// product_id 별 조회 결과 가져오기
	public List<ReservationUserComment> findByProductId(Integer productId, Integer start) {
		Map<String, Integer> params = new HashMap<>();
		params.put("product_id", productId);
		params.put("start", start);
		
		List<ReservationUserComment> comments = jdbc.query(SELECT_COMMENT_BY_PRODUCT_ID, params, rowMapper);

		return comments;
	}
	
	public List<ReservationUserCommentImage> findImages(Integer commentId) {
		Map<String, Integer> params = new HashMap<>();
		params.put("comment_id", commentId);
		
		List<ReservationUserCommentImage> images = jdbc.query(SELECT_COMMENT_IMAGE, params, imageRowMapper);
		return images;
	}

	public Integer getTotalCount() {
		return jdbc.queryForObject(SELECT_TOTAL_COUNT_ALL_PRODUCT, Collections.emptyMap(), Integer.class);

	}
	
	public Integer getTotalCountByProductId(Integer productId) {
		Map<String, Integer> params = new HashMap<>();
		params.put("product_id", productId);

		return jdbc.queryForObject(SELECT_TOTAL_COUNT_BY_PRODUCT_ID, params, Integer.class);

	}

}

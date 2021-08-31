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
import kr.or.connect.reservation.dto.ReservationUserCommentImage;

@Repository
public class CommentDao {
	private NamedParameterJdbcTemplate jdbc;
	private SimpleJdbcInsert insertAction;
	private RowMapper<ReservationUserComment> rowMapper = BeanPropertyRowMapper.newInstance(ReservationUserComment.class);
	private RowMapper<ReservationUserCommentImage> imageRowMapper = BeanPropertyRowMapper.newInstance(ReservationUserCommentImage.class);
	
	public CommentDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
		this.insertAction = new SimpleJdbcInsert(dataSource).withTableName("reservation_user_comment")
				.usingGeneratedKeyColumns("id");
	}

	// product_id 별 조회 결과 가져오기
	public List<ReservationUserComment> findByProductId(Integer productId, Integer start) {
		Map<String, Integer> params = new HashMap<>();
		params.put("product_id", productId);
		params.put("start", start);
		
		List<ReservationUserComment> comments;
		
		// 상품id N
		if(productId == 0) {
			comments = jdbc.query(SELECT_COMMENT_ALL, params, rowMapper);
		}
		
		// 상품id Y
		else {
			comments = jdbc.query(SELECT_COMMENT_BY_PRODUCT_ID, params, rowMapper);
		}
		
		// 댓글 이미지 가져오기
		for(int i=0; i<comments.size(); i++) {
			ReservationUserComment comment = comments.get(i);
			
			params.put("comment_id", comment.getId());
			List<ReservationUserCommentImage> images = jdbc.query(SELECT_COMMENT_IMAGE, params, imageRowMapper);
			
			comment.setReservationUserCommentImages(images);
		}

		return comments;

	}

	// get Total Count
	public Integer getTotalCount(Integer productId) {
		Map<String, Integer> params = new HashMap<>();
		params.put("product_id", productId);
		
		// 상품id N
		if (productId == 0) {
			return jdbc.queryForObject(SELECT_TOTAL_COUNT_ALL_PRODUCT, params, Integer.class);
		} 
		
		// 상품id Y
		else {			
			return jdbc.queryForObject(SELECT_TOTAL_COUNT_BY_PRODUCT_ID, params, Integer.class);
		}
	}

	public Integer getCommentCount(Integer productId, Integer start) {
		Map<String, Integer> params = new HashMap<>();
		params.put("productId", productId);
		params.put("start", start);

		// 상품id N
		if (productId == 0) {
			return jdbc.queryForObject(SELECT_COMMENT_COUNT_ALL_PRODUCT, params, Integer.class);
		} 
		
		// 상품id Y
		else {
			return jdbc.queryForObject(SELECT_COMMENT_COUNT_ALL_PRODUCT, params, Integer.class);
		}
	}

}

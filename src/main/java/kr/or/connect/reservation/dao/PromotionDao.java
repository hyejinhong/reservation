package kr.or.connect.reservation.dao;

import static kr.or.connect.reservation.dao.PromotionDaoSqls.*;

import java.util.Collections;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservation.dto.Category;
import kr.or.connect.reservation.dto.Promotion;

@Repository
public class PromotionDao {
	private NamedParameterJdbcTemplate jdbc;
	private SimpleJdbcInsert insertAction;
	private RowMapper<Promotion> rowMapper = BeanPropertyRowMapper.newInstance(Promotion.class);
	
	public PromotionDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
		this.insertAction = new SimpleJdbcInsert(dataSource)
				.withTableName("promotion")
				.usingGeneratedKeyColumns("id");
	}
	
	// 모든 카테고리 가져오기
	public List<Promotion> selectAll() {
		return jdbc.query(SELECT_ALL, rowMapper);
	}

	// 개수 반환
	public Integer selectSize() {
		return jdbc.queryForObject(SELECT_SIZE, Collections.emptyMap(), Integer.class);
	}
}

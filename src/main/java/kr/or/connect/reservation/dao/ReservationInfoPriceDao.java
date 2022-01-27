package kr.or.connect.reservation.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservation.dto.ReservationInfoPrice;
import static kr.or.connect.reservation.dao.sqls.ReservationInfoPriceDaoSqls.*;

@Repository
public class ReservationInfoPriceDao {

	private NamedParameterJdbcTemplate jdbc;
	private SimpleJdbcInsert insertAction;
	private RowMapper<ReservationInfoPrice> rowMapper = BeanPropertyRowMapper.newInstance(ReservationInfoPrice.class);
	
	public ReservationInfoPriceDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
		this.insertAction = new SimpleJdbcInsert(dataSource)
				.withTableName("reservation_info_price")
				.usingGeneratedKeyColumns("id");
	}

	public int insert(ReservationInfoPrice reservationInfoPrice) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(reservationInfoPrice);
		return insertAction.executeAndReturnKey(params).intValue();
	}
	
	public List<ReservationInfoPrice> getPrices(int reservationInfoId) {
		Map<String, Object> params = new HashMap<>();
		params.put("reservation_info_id", reservationInfoId);
		return jdbc.query(SELECT_BY_INFO_ID, params, rowMapper);
	}

	public int getSumPrice(int reservationInfoId) {
		Map<String, Object> params = new HashMap<>();
		params.put("reservation_info_id", reservationInfoId);
		
		return jdbc.queryForObject(SELECT_SUM_PRICE, params, Integer.class);
	}
}

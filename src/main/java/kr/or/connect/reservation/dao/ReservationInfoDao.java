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

import kr.or.connect.reservation.dto.ReservationInfo;
import kr.or.connect.reservation.dto.ReservationInfoResult;

import static kr.or.connect.reservation.dao.sqls.ReservationInfoSqls.*;

@Repository
public class ReservationInfoDao {
	private NamedParameterJdbcTemplate jdbc;
	private SimpleJdbcInsert insertAction;
	private RowMapper<ReservationInfo> rowMapper = BeanPropertyRowMapper.newInstance(ReservationInfo.class);
	private RowMapper<ReservationInfoResult> resultRowMapper = BeanPropertyRowMapper.newInstance(ReservationInfoResult.class);
	
	public ReservationInfoDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
		this.insertAction = new SimpleJdbcInsert(dataSource)
				.withTableName("reservation_info")
				.usingGeneratedKeyColumns("id");
	}

	
	public int insert(ReservationInfo reservationInfo) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(reservationInfo);
		return insertAction.executeAndReturnKey(params).intValue();
	}

	public ReservationInfo findById(int id) {
		Map<String, Object> params = new HashMap<>();
		params.put("id", id);
		
		return jdbc.queryForObject(SELECT_BY_ID, params, rowMapper);
	}

	// 회원별 예약 목록
	public List<ReservationInfo> findByUserId(int userId) {
		Map<String, Object> params = new HashMap<>();
		params.put("user_id", userId);
		
		return jdbc.query(SELECT_BY_USER_ID, params, rowMapper);
	}

	// 예약 취소
	/* Q. 여기서 저는 쿼리문 자체를 cancel_flag 만 변경하도록 작성했는데, 
	 * 어떤 parameter가 들어오더라도 해당 이름을 가진 변수의 값을 수정할 수 있도록 짜는 것이 더 RESTFUL한 것인지 궁금합니다.
	 * 그렇다면, 그렇게 구현하려면 어떻게 해야하는지도 궁금합니다 ㅠ_ㅠ
	 */
	public String update(int id) {
		Map<String, Object> params = new HashMap<>();
		params.put("id", id);
		
		return jdbc.update(UPDATE_BY_ID, params) == 0 ? "fail" : "success";
	}

	public ReservationInfoResult findResultById(int id) {
		Map<String, Object> params = new HashMap<>();
		params.put("id", id);

		return jdbc.queryForObject(SELECT_RESULT_BY_ID, params, resultRowMapper);
	}
}

package kr.or.connect.reservation.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservation.dto.DisplayInfoImage;

import static kr.or.connect.reservation.dao.DisplayInfoImageDaoSqls.*;

@Repository
public class DisplayInfoImageDao {
	private NamedParameterJdbcTemplate jdbc;
	private SimpleJdbcInsert insertAction;
	private RowMapper<DisplayInfoImage> rowMapper = BeanPropertyRowMapper.newInstance(DisplayInfoImage.class);
	
	public DisplayInfoImageDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
		this.insertAction = new SimpleJdbcInsert(dataSource)
				.withTableName("display_info_image")
				.usingGeneratedKeyColumns("id");
	}

	public List<DisplayInfoImage> listByDisplayInfoId(Integer displayInfoId) {
		Map<String, Object> params = new HashMap<>();
		params.put("display_info_id", displayInfoId);
		
		return jdbc.query(SELECT_BY_DISPLAY_INFO_ID, params, rowMapper);
	}

}
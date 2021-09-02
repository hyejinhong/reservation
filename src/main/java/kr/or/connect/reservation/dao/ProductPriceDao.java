package kr.or.connect.reservation.dao;

import static kr.or.connect.reservation.dao.sqls.ProductPriceDaoSqls.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservation.dto.ProductPrice;

@Repository
public class ProductPriceDao {
	private NamedParameterJdbcTemplate jdbc;
	private SimpleJdbcInsert insertAction;
	private RowMapper<ProductPrice> rowMapper = BeanPropertyRowMapper.newInstance(ProductPrice.class);
	
	public ProductPriceDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
		this.insertAction = new SimpleJdbcInsert(dataSource)
				.withTableName("product_price")
				.usingGeneratedKeyColumns("id");
	}

	public List<ProductPrice> listByProductId(Integer productId) {
		Map<String, Object> params = new HashMap<>();
		params.put("product_id", productId);
		
		return jdbc.query(SELECT_BY_PRODUCT_ID, params, rowMapper);
	}

}

package kr.or.connect.reservation.dao.sqls;

public class ProductPriceDaoSqls {

	public static final String SELECT_BY_PRODUCT_ID =
			"SELECT price.id, price.product_id, price.price_type_name, price.price, price.discount_rate, price.create_date, price.modify_date FROM product_price price WHERE price.product_id=:product_id";
}

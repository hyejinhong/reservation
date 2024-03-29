package kr.or.connect.reservation.dao.sqls;

public class ReservationInfoSqls {
	public static final String SELECT_BY_ID =
			"SELECT id, product_id, cancel_flag, display_info_id, user_id, reservation_date, create_date, modify_date FROM reservation_info WHERE id=:id";

	public static final String SELECT_BY_USER_ID =
			"SELECT info.id, info.product_id, info.display_info_id, info.cancel_flag, product.description AS productDescription, product.content AS productContent, info.user_id, info.reservation_date, info.create_date, info.modify_date FROM reservation_info AS info LEFT JOIN reservation_info_price AS rip ON info.id=rip.reservation_info_id RIGHT JOIN product_price AS pp ON rip.product_price_id=pp.id LEFT JOIN product ON pp.product_id = product.id GROUP BY rip.reservation_info_id HAVING info.user_id=:user_id";
	
	public static final String UPDATE_BY_ID =
			"UPDATE reservation_info SET cancel_flag=1 WHERE id=:id";
	
	public static final String SELECT_RESULT_BY_ID = 
			"SELECT id, product_id, cancel_flag, display_info_id, user_id, reservation_date, create_date, modify_date FROM reservation_info WHERE id=:id";
}

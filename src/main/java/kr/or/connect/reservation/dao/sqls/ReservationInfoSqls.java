package kr.or.connect.reservation.dao.sqls;

public class ReservationInfoSqls {
	public static final String SELECT_BY_ID =
			"SELECT id, product_id, cancel_flag, display_info_id, user_id, reservation_date, create_date, modify_date FROM reservation_info WHERE id=:id";

	public static final String SELECT_BY_USER_ID =
			"SELECT info.id, info.product_id, info.display_info_id, info.cancel_flag, product.description AS productDescription, product.content AS productContent, info.user_id, info.reservation_date, info.create_date, info.modify_date FROM reservation_info AS info JOIN product ON info.product_id = product.id WHERE info.user_id=:user_id";
}

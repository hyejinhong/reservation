package kr.or.connect.reservation.dao.sqls;

public class ReservationInfoSqls {
	public static final String SELECT_BY_ID =
			"SELECT id, product_id, cancel_flag, display_info_id, user_id, reservation_date, create_date, modify_date FROM reservation_info WHERE id=:id";
}

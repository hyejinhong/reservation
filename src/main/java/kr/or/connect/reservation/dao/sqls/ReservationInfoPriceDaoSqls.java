package kr.or.connect.reservation.dao.sqls;

public class ReservationInfoPriceDaoSqls {

	public static final String SELECT_BY_INFO_ID =
			"SELECT id, reservation_info_id, product_price_id, count FROM reservation_info_price WHERE reservation_info_id=:reservation_info_id";
}

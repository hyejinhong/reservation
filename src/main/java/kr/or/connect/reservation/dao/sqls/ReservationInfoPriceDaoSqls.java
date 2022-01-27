package kr.or.connect.reservation.dao.sqls;

public class ReservationInfoPriceDaoSqls {

	public static final String SELECT_BY_INFO_ID =
			"SELECT id, reservation_info_id, product_price_id, count FROM reservation_info_price WHERE reservation_info_id=:reservation_info_id";
	
	public static final String SELECT_SUM_PRICE = 
			"SELECT sum(pp.price * (100-pp.discount_rate) / 100) FROM reservation_info_price rip JOIN product_price pp ON rip.product_price_id=pp.id WHERE rip.reservation_info_id=:reservation_info_id";
}

package kr.or.connect.reservation.dao;

public class CommentDaoSqls {

	public static final String SELECT_TOTAL_COUNT = 
			"SELECT COUNT(*) "
			+ "FROM reservation_info info INNER JOIN reservation_user_comment comment ON info.id=comment.reservation_info_id "
			+ "INNER JOIN user ON info.user_id=user.id "
			+ "LEFT OUTER JOIN reservation_user_comment_image image ON info.id=image.reservation_info_id";

	public static final String SELECT_COMMENT_COUNT = 
			"SELECT COUNT(*) "
			+ "FROM reservation_info info INNER JOIN reservation_user_comment comment ON info.id=comment.reservation_info_id "
			+ "INNER JOIN user ON info.user_id=user.id "
			+ "LEFT OUTER JOIN reservation_user_comment_image image ON info.id=image.reservation_info_id "
			+ "WHERE info.product_id=:product_id";
	
	public static final String SELECT_ALL = 
			"SELECT comment.id, comment.product_id, comment.reservation_info_id, "
			+ "comment.score, user.email AS reservation_email, comment.comment, "
			+ "comment.create_date, comment.modify_date "
			+ "FROM reservation_info info INNER JOIN reservation_user_comment comment ON info.id=comment.reservation_info_id "
			+ "INNER JOIN user ON info.user_id=user.id "
			+ "LEFT OUTER JOIN reservation_user_comment_image image ON info.id=image.reservation_info_id";

	public static final String SELECT_COMMENT_BY_PRODUCT_ID = 
			"SELECT comment.id, comment.product_id, comment.reservation_info_id, "
			+ "comment.score, user.email AS reservation_email, comment.comment, "
			+ "comment.create_date, comment.modify_date "
			+ "FROM reservation_info info INNER JOIN reservation_user_comment comment ON info.id=comment.reservation_info_id "
			+ "INNER JOIN user ON info.user_id=user.id "
			+ "LEFT OUTER JOIN reservation_user_comment_image image ON info.id=image.reservation_info_id "
			+ "WHERE info.product_id=:product_id";
}

package kr.or.connect.reservation.dao;

public class CommentDaoSqls {

	// Total Count - 모든 상품
	public static final String SELECT_TOTAL_COUNT_ALL_PRODUCT = 
			"SELECT COUNT(*) "
			+ "FROM reservation_info info INNER JOIN reservation_user_comment comment ON info.id=comment.reservation_info_id "
			+ "INNER JOIN user ON info.user_id=user.id "
			+ "LEFT OUTER JOIN reservation_user_comment_image image ON info.id=image.reservation_info_id ";
	
	// Total Count - 상품 별
	public static final String SELECT_TOTAL_COUNT_BY_PRODUCT_ID =		
			"SELECT COUNT(*) "
			+ "FROM reservation_info info INNER JOIN reservation_user_comment comment ON info.id=comment.reservation_info_id "
			+ "INNER JOIN user ON info.user_id=user.id "
			+ "LEFT OUTER JOIN reservation_user_comment_image image ON info.id=image.reservation_info_id "
			+ "WHERE info.product_id=:product_id ";

	// Comment Count - 읽어온 댓글 수, 모든 상품별
	public static final String SELECT_COMMENT_COUNT_ALL_PRODUCT = 
			"SELECT COUNT(*) "
			+ "FROM reservation_info info INNER JOIN reservation_user_comment comment ON info.id=comment.reservation_info_id "
			+ "INNER JOIN user ON info.user_id=user.id "
			+ "LEFT OUTER JOIN reservation_user_comment_image image ON info.id=image.reservation_info_id "
			+ "LIMIT :start, 18446744073709551615";
	// limit에서 infinit row를 출력하고 싶을 때 이렇게 하라고 MySQL Official Doc에 써있음.. 믿을 수 없다.

	// Comment Count - 읽어온 댓글 수, 카테고리 별
	public static final String SELECT_COMMENT_COUNT_BY_PRODUCT_ID =
			"SELECT COUNT(*) "
			+ "FROM reservation_info info INNER JOIN reservation_user_comment comment ON info.id=comment.reservation_info_id "
			+ "INNER JOIN user ON info.user_id=user.id "
			+ "LEFT OUTER JOIN reservation_user_comment_image image ON info.id=image.reservation_info_id "
			+ "WHERE info.product_id=:product_id "
			+ "LIMIT :start, 18446744073709551615";
	// limit에서 infinit row를 출력하고 싶을 때 이렇게 하라고 MySQL Official Doc에 써있음.. 믿을 수 없다.

	// 모든 댓글 가져오기
	public static final String SELECT_COMMENT_ALL = 
			"SELECT comment.id, comment.product_id, comment.reservation_info_id, "
			+ "comment.score, user.email AS reservation_email, comment.comment, "
			+ "comment.create_date, comment.modify_date, "
			
			+ "image.id AS 'reservation_user_comment_images.id', image.reservation_info_id AS 'reservation_user_comment_image.reservation_info_id', " 
			+ "image.reservation_user_comment_id AS 'reservation_user_comment_image.reservation_user_comment_id', "
			+ "image.file_id AS 'reservation_user_comment_image.file_id' "
			
			
			+ "FROM reservation_info info INNER JOIN reservation_user_comment comment ON info.id=comment.reservation_info_id "
			+ "INNER JOIN user ON info.user_id=user.id "
			+ "LEFT OUTER JOIN reservation_user_comment_image image ON info.id=image.reservation_info_id";

	// 카테고리별 댓글 가져오기
	public static final String SELECT_COMMENT_BY_PRODUCT_ID = 
			"SELECT comment.id, comment.product_id, comment.reservation_info_id, "
			+ "comment.score, user.email AS reservation_email, comment.comment, "
			+ "comment.create_date, comment.modify_date, "

			+ "image.id AS 'reservation_user_comment_images.id', image.reservation_info_id AS 'reservation_user_comment_image.reservation_info_id', " 
			+ "image.reservation_user_comment_id AS 'reservation_user_comment_image.reservation_user_comment_id', "
			+ "image.file_id AS 'reservation_user_comment_image.file_id' "

			+ "FROM reservation_info info INNER JOIN reservation_user_comment comment ON info.id=comment.reservation_info_id "
			+ "INNER JOIN user ON info.user_id=user.id "
			+ "LEFT OUTER JOIN reservation_user_comment_image image ON info.id=image.reservation_info_id "
			+ "WHERE info.product_id=:product_id";
}

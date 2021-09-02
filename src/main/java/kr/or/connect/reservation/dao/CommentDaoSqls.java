package kr.or.connect.reservation.dao;

public class CommentDaoSqls {
	// 모든 댓글 가져오기
	public static final String SELECT_COMMENT_ALL = 
			"SELECT comment.id, comment.product_id, comment.reservation_info_id, comment.score, user.email AS reservation_email, comment.comment, comment.create_date, comment.modify_date FROM reservation_user_comment comment INNER JOIN reservation_info info ON info.id=comment.reservation_info_id INNER JOIN user ON info.user_id=user.id LIMIT :start, 5";

	// 카테고리별 댓글 가져오기
	public static final String SELECT_COMMENT_BY_PRODUCT_ID = 
			"SELECT comment.id, comment.product_id, comment.reservation_info_id, comment.score, user.email AS reservation_email, comment.comment, comment.create_date, comment.modify_date FROM reservation_user_comment comment INNER JOIN reservation_info info ON info.id=comment.reservation_info_id INNER JOIN user ON info.user_id=user.id WHERE comment.product_id=:product_id LIMIT :start, 5";
	
	// comment의 댓글 이미지 가져오기
	public static final String SELECT_COMMENT_IMAGE =
			"SELECT id, reservation_info_id, reservation_user_comment_id, file_id FROM reservation_user_comment_image WHERE reservation_user_comment_id=:comment_id";
	
	// Total Count - 모든 상품의 댓글 갯수
	public static final String SELECT_TOTAL_COUNT_ALL_PRODUCT = 
			"SELECT COUNT(*) FROM reservation_user_comment";
	
	// Total Count - 상품 별 댓글 갯수
	public static final String SELECT_TOTAL_COUNT_BY_PRODUCT_ID =		
			"SELECT COUNT(*) FROM reservation_user_comment WHERE product_id=:product_id";	
}

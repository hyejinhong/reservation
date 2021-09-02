package kr.or.connect.reservation.dao;


public class DisplayInfoDaoSqls {
	
	public static final String SELECT_ONE = 
			"SELECT display_info.id, category.id AS category_id, display_info.id AS display_info_id, category.name AS category_name, product.description AS product_description, product.content AS product_content, product.event AS product_event, opening_hours, place_name, place_lot, place_street, tel, homepage, email, display_info.create_date, display_info.modify_date, file_info.id AS file_id FROM display_info LEFT JOIN product ON display_info.product_id = product.id LEFT JOIN category ON product.category_id = category.id LEFT JOIN product_image ON product_image.product_id = product.id LEFT JOIN file_info ON product_image.file_id = file_info.id WHERE display_info.id=:display_info_id";

	public static final String SELECT_ALL = 
			"SELECT display_info.id, category.id AS category_id, display_info.id AS display_info_id, category.name AS category_name, product.description AS product_description, product.content AS product_content, product.event AS product_event, opening_hours, place_name, place_lot, place_street, tel, homepage, email, display_info.create_date, display_info.modify_date, file_info.id AS file_id FROM display_info LEFT JOIN product ON display_info.product_id = product.id LEFT JOIN category ON product.category_id = category.id LEFT JOIN product_image ON product_image.product_id = product.id LEFT JOIN file_info ON product_image.file_id = file_info.id WHERE product_image.type = 'ma' LIMIT :start, 18446744073709551615";
	// 위 LIMIT 절 참고 : https://stackoverflow.com/questions/255517/mysql-offset-infinite-rows
	
	public static final String SELECT_BY_CATEGORY_ID = 
			"SELECT display_info.id, category.id AS category_id, display_info.id AS display_info_id, category.name AS category_name, product.description AS product_description, product.content AS product_content, product.event AS product_event, opening_hours, place_name, place_lot, place_street, tel, homepage, email, display_info.create_date, display_info.modify_date, file_info.id AS file_id FROM display_info LEFT JOIN product ON display_info.product_id = product.id LEFT JOIN category ON product.category_id = category.id LEFT JOIN product_image ON product_image.product_id = product.id LEFT JOIN file_info ON product_image.file_id = file_info.id WHERE category_id = :category_id AND product_image.type = 'ma' LIMIT :start, 18446744073709551615";
	
	public static final String DELETE_BY_ID = "DELETE FROM display_info WHERE id = :id";
	
	public static final String SELECT_TOTAL_COUNT = 
			"SELECT count(DISTINCT product_id) FROM display_info INNER JOIN product ON display_info.product_id=product.id INNER JOIN category ON product.category_id = category.id WHERE category_id=:category_id";
	
	public static final String SELECT_TOTAL_COUNT_ALL_CATEGORY =
			"SELECT count(DISTINCT product_id) FROM display_info INNER JOIN product ON display_info.product_id=product.id INNER JOIN category ON product.category_id = category.id";
	
	public static final String SELECT_PRODUCT_COUNT_ALL_CATEGORY =
			"SELECT count(DISTINCT product_id) FROM display_info INNER JOIN product ON display_info.product_id=product.id INNER JOIN category ON product.category_id = category.id";

		public static final String SELECT_AVG_SCORE =
			"SELECT AVG(comment.score) AS avgScore FROM reservation_user_comment comment JOIN reservation_info info ON comment.reservation_info_id=info.id WHERE info.id=:display_info_id";

}

package kr.or.connect.reservation.dao.sqls;

public class ProductDaoSqls {

	public final static String SELECT_ALL_PRODUCT =
			"SELECT product.id, category.id AS category_id, display_info.id AS display_info_id, category.name, product.description, product.content, product.event, opening_hours, place_name, place_lot, place_street, tel, homepage, email, display_info.create_date, display_info.modify_date, file_info.id AS file_id FROM display_info LEFT JOIN product ON display_info.product_id = product.id LEFT JOIN category ON product.category_id = category.id LEFT JOIN product_image ON product_image.product_id = product.id LEFT JOIN file_info ON product_image.file_id = file_info.id WHERE product_image.type = 'ma' GROUP BY product.id LIMIT :start, 18446744073709551615";
	// 위 LIMIT 절 참고 : https://stackoverflow.com/questions/255517/mysql-offset-infinite-rows
	
	public final static String SELECT_BY_CATEGORY_ID =
			"SELECT product.id, category.id AS category_id, display_info.id AS display_info_id, category.name, product.description, product.content, product.event, opening_hours, place_name, place_lot, place_street, tel, homepage, email, display_info.create_date, display_info.modify_date, file_info.id AS file_id FROM display_info LEFT JOIN product ON display_info.product_id = product.id LEFT JOIN category ON product.category_id = category.id LEFT JOIN product_image ON product_image.product_id = product.id LEFT JOIN file_info ON product_image.file_id = file_info.id WHERE category_id = :category_id AND product_image.type = 'ma' GROUP BY product.id LIMIT :start, 18446744073709551615";
	
	public final static String SELECT_BY_DISPLAY_INFO_ID =
			"SELECT product.id, product.category_id, display_info.id as display_info_id, category.name as name, product.description, product.content, product.event, display_info.opening_hours, display_info.place_name, display_info.place_lot, display_info.place_street, display_info.tel, display_info.homepage, display_info.email, product.create_date, product.modify_date, product_image.file_id as file_id FROM product JOIN display_info ON product.id=display_info.product_id JOIN category ON product.category_id=category.id JOIN product_image ON product.id=product_image.product_id WHERE display_info.id=:display_info_id LIMIT 1";
	
	public static final String SELECT_TOTAL_COUNT = 
			"SELECT count(DISTINCT product_id) FROM display_info INNER JOIN product ON display_info.product_id=product.id INNER JOIN category ON product.category_id = category.id WHERE category_id=:category_id";
	
	public static final String SELECT_TOTAL_COUNT_ALL_CATEGORY =
			"SELECT count(DISTINCT product_id) FROM display_info INNER JOIN product ON display_info.product_id=product.id INNER JOIN category ON product.category_id = category.id";
}

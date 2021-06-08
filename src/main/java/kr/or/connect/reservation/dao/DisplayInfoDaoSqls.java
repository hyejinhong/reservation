package kr.or.connect.reservation.dao;


public class DisplayInfoDaoSqls {
	public static final String SELECT_ALL = 
			"SELECT display_info.id, category.id AS category_id, display_info.id AS display_info_id, category.name AS category_name, "
			+ "product.description AS product_description, product.content AS product_content, product.event AS product_event, "
			+ "opening_hours, place_name, place_lot, place_street, tel, homepage, email, display_info.create_date, display_info.modify_date, "
			+ "file_info.id AS file_id "
			+ "FROM display_info LEFT JOIN product ON display_info.product_id = product.id "
			+ "LEFT JOIN category ON product.category_id = category.id "
			+ "LEFT JOIN product_image ON product_image.product_id = product.id "
			+ "LEFT JOIN file_info ON product_image.file_id = file_info.id "
			+ "WHERE product_image.type = 'ma'";

	public static final String SELECT_BY_CATEGORY_ID = 
			"SELECT display_info.id, category.id AS category_id, display_info.id AS display_info_id, category.name AS category_name, "
			+ "product.description AS product_description, product.content AS product_content, product.event AS product_event, "
			+ "opening_hours, place_name, place_lot, place_street, tel, homepage, email, display_info.create_date, display_info.modify_date, "
			+ "file_info.id AS file_id "
			+ "FROM display_info LEFT JOIN product ON display_info.product_id = product.id "
			+ "LEFT JOIN category ON product.category_id = category.id "
			+ "LEFT JOIN product_image ON product_image.product_id = product.id "
			+ "LEFT JOIN file_info ON product_image.file_id = file_info.id "
			+ "WHERE category_id = :category_id AND product_image.type = 'ma'";
	
	public static final String DELETE_BY_ID = "DELETE FROM display_info WHERE id = :id";
	
	public static final String SELECT_TOTAL_COUNT = 
			"SELECT count(*) "
			+ "FROM display_info INNER JOIN product ON display_info.product_id=product.id "
			+ "INNER JOIN category ON product.category_id = category.id "
			+ "WHERE category_id=:category_id";
	
	public static final String SELECT_TOTAL_COUNT_ALL_CATEGORY =
			"SELECT count(*) "
			+ "FROM display_info INNER JOIN product ON display_info.product_id=product.id "
			+ "INNER JOIN category ON product.category_id = category.id";		

	public static final String SELECT_PRODUCT_COUNT = 
			"SELECT count(*) "
			+ "FROM (SELECT product.id, count(product.id) FROM display_info INNER JOIN product ON display_info.product_id = product.id "
			+ "INNER JOIN category ON product.category_id = category.id "
			+ "WHERE category_id=:category_id "
			+ "GROUP BY product.id) a";
	
	public static final String SELECT_PRODUCT_COUNT_ALL_CATEGORY =
			"SELECT count(*) "
			+ "FROM (SELECT product.id, count(product.id) FROM display_info INNER JOIN product ON display_info.product_id = product.id "
			+ "INNER JOIN category ON product.category_id = category.id "
//			+ "WHERE category_id=:category_id "
			+ "GROUP BY product.id) a";
			

}

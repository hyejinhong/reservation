package kr.or.connect.reservation.dao.sqls;

public class PromotionDaoSqls {

	public static String SELECT_ALL =
			"SELECT promotion.id, product.id AS product_id, category.id AS category_id, category.name AS category_name, product.description AS description, file_info.id AS file_id FROM promotion INNER JOIN product ON promotion.product_id = product.id INNER JOIN category ON product.category_id = category.id LEFT JOIN product_image ON product_image.product_id = product.id LEFT JOIN file_info ON product_image.file_id = file_info.id WHERE product_image.type = 'ma'";

	public static String SELECT_SIZE = "SELECT COUNT(*) FROM promotion";
}

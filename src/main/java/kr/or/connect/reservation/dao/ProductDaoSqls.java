package kr.or.connect.reservation.dao;

public class ProductDaoSqls {

	public final static String SELECT_ALL_PRODUCT =
			"SELECT product.id, product.category_id, display_info.id as display_info_id, category.name as name, "
			+ "product.description, product.content, product.event, display_info.opening_hours, "
			+ "display_info.place_name, display_info.place_lot, display_info.place_street, "
			+ "display_info.tel, display_info.homepage, display_info.email, "
			+ "product.create_date, product.modify_date, product_image.file_id as file_id "
			
			+ "FROM product JOIN display_info ON product.id=display_info.product_id "
			+ "JOIN category ON product.category_id=category.id "
			+ "JOIN product_image ON product.id=product_image.product_id";
}

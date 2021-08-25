package kr.or.connect.reservation.dao;

public class ProductImageDaoSqls {

	public static final String SELECT_BY_PRODUCT_ID = 
			"SELECT image.product_id, image.id AS product_image_id, image.type, "
			+ "file_info.id AS file_info_id, file_info.file_name, file_info.save_file_name, "
			+ "file_info.content_type, file_info.delete_flag, file_info.create_date, file_info.modify_date "
			+ "FROM product_image image join file_info ON image.file_id=file_info.id "
			+ "WHERE image.product_id=:product_id";
}

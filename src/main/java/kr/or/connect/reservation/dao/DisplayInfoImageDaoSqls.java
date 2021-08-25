package kr.or.connect.reservation.dao;

public class DisplayInfoImageDaoSqls {
	public static final String SELECT_BY_DISPLAY_INFO_ID = 
			"SELECT image.id, image.display_info_id, image.file_id, "
			+ "file_info.file_name, file_info.save_file_name, file_info.content_type, file_info.delete_flag, "
			+ "file_info.create_date, file_info.modify_date "
			+ "FROM display_info_image image JOIN file_info ON image.file_id=file_info.id "
			+ "WHERE image.display_info_id=:display_info_id";
}

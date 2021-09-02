package kr.or.connect.reservation.dao.sqls;

public class CategoryDaoSqls {
	public static final String SELECT_ALL = "SELECT c.id, c.name, count(*) as count FROM category c LEFT JOIN product p ON c.id = p.category_id LEFT JOIN display_info d ON p.id = d.product_id GROUP BY c.id";

	public static final String DELETE_BY_ID = "DELETE FROM category WHERE id = :id";
}

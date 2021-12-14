package kr.or.connect.reservation.dao.sqls;

public class UserRoleDaoSqls {
	public static final String SELECT_ALL_BY_EMAIL = "SELECT id, user_id, role_name FROM user_role WHERE email=:email";
}

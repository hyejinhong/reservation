package kr.or.connect.reservation.dao.sqls;

public class UserRoleDaoSqls {
	public static final String SELECT_ALL_BY_EMAIL = "SELECT user_role.id, user_role.user_id, user_role.role_name FROM user_role JOIN user ON user_role.user_id=user.id WHERE user.email=:email";
}

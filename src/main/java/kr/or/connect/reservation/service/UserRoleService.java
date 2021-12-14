package kr.or.connect.reservation.service;

import java.util.List;

import kr.or.connect.reservation.dto.UserRole;

public interface UserRoleService {
	List<UserRole> getRoles(String email);
}

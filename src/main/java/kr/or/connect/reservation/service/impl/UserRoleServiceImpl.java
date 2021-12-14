package kr.or.connect.reservation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.connect.reservation.dao.UserRoleDao;
import kr.or.connect.reservation.dto.UserRole;
import kr.or.connect.reservation.service.UserRoleService;

@Service
public class UserRoleServiceImpl implements UserRoleService {
	@Autowired
	UserRoleDao userRoleDao;
	
	@Override
	public List<UserRole> getRoles(String email) {
		return userRoleDao.getRolesByEmail(email);
	}

}

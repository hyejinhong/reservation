package kr.or.connect.reservation.service.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import kr.or.connect.reservation.dao.UserDao;
import kr.or.connect.reservation.dao.UserRoleDao;
import kr.or.connect.reservation.dto.User;
import kr.or.connect.reservation.dto.UserRole;
import kr.or.connect.reservation.service.UserRoleService;
import kr.or.connect.reservation.service.UserService;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	UserDbService userDbService;
	
	@Override
	public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
		UserEntity user = userDbService.getUser(loginId);
		System.out.println("입력 아이디 : " + loginId);
		System.out.println("비밀번호는? " + user.getPassword());
		
		if(user == null) {
			throw new UsernameNotFoundException("그런 유저는 없습니다.");
		}
		
		// CustomUserDetails 객체로 전달
		CustomUserDetails cutomUserDetails = new CustomUserDetails();
		cutomUserDetails.setUsername(user.getLoginUserId());
		cutomUserDetails.setPassword(user.getPassword());
		
		List<UserRoleEntity> roles = userDbService.getUserRoles(loginId);
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		if(roles != null) {
			// 모든 권한에 대하여...
			for(UserRoleEntity role : roles) {
				// authorities 정보에 하나씩 추가해준다.
				// MemberRole 이름은 "ROLE_"로 시작해야 한다.
				authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
			}
		}
		
		// CustomUserDetails 객체에 방금 받아온 권한 목록(authorities)를 설정한다.
		cutomUserDetails.setAuthorities(authorities);
		cutomUserDetails.setEnabled(true);
		cutomUserDetails.setAccountNonExpired(true);
		cutomUserDetails.setAccountNonLocked(true);
		cutomUserDetails.setCredentialsNonExpired(true);
		
		return cutomUserDetails;
	}

}

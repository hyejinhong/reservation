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

import kr.or.connect.reservation.dto.User;
import kr.or.connect.reservation.dto.UserRole;
import kr.or.connect.reservation.service.UserRoleService;
import kr.or.connect.reservation.service.UserService;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	UserService userService;
	@Autowired
	UserRoleService userRoleService;
	
	@Override
	public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
		User customUser = userService.getUserByEmail(loginId);
		if(customUser != null) {
			throw new UsernameNotFoundException("사용자가 입력한 아이디에 해당하는 정보가 없습니다.");
		}
		
		// CustomUserDetails 객체로 전달
		CustomUserDetails customUserDetails = new CustomUserDetails();
		customUserDetails.setUsername(customUser.getEmail());
		customUserDetails.setPassword(customUser.getPassword());
		
		List<UserRole> customRoles = userRoleService.getRoles(loginId);
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		if(customRoles != null) {
			for(UserRole role : customRoles) {
				authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
			}
		}
		
		customUserDetails.setAuthorities(authorities);
		customUserDetails.setEnabled(true);
		customUserDetails.setAccountNonExpired(true);
		customUserDetails.setAccountNonLocked(true);
		customUserDetails.setCredentialsNonExpired(true);

		return customUserDetails;
	}

}

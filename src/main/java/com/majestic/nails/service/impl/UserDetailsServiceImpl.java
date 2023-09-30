package com.majestic.nails.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.majestic.nails.dto.UserDto;
import com.majestic.nails.entity.User;
import com.majestic.nails.entity.UserRole;
import com.majestic.nails.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.majestic.nails.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	private static final int salt = 30;

	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		Set<GrantedAuthority> grantedAuthorities = new HashSet();
		List<UserRole> roles = roleRepository.findByUsername(username);
		for (UserRole role : roles){
			System.out.println(role.getUserRole());
			grantedAuthorities.add(new SimpleGrantedAuthority(role.getUserRole()));
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
	}

	@Transactional
	public User createUser(UserDto dto) {
		User user = new User();
		UserRole role = new UserRole();
		user.setUsername(dto.getUsername());
		user.setPassword(passwordEncoder.encode(dto.getPassword()));
		role.setUsername(dto.getUsername());
		role.setUserRole(dto.getRole());
		userRepository.save(user);
		roleRepository.save(role);
		return user;
	}
}

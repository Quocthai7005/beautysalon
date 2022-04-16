package com.doctor.spa.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.doctor.spa.dto.UserDto;
import com.doctor.spa.util.PasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.doctor.spa.entity.User;
import com.doctor.spa.entity.UserRole;
import com.doctor.spa.repository.RoleRepo;
import com.doctor.spa.repository.UserRepo;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	@Autowired
	private UserRepo userRepository;

	@Autowired
	private RoleRepo roleRepository;

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

	public User createUser(UserDto dto) {
		User user = new User();
		user.setUsername(dto.getUsername());
		user.setPassword(PasswordEncryptor.generateSecurePassword(dto.getPassword(), PasswordEncryptor.getSaltvalue(salt)));
		userRepository.save(user);
		return user;
	}
}

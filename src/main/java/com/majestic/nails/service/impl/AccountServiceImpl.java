package com.majestic.nails.service.impl;

import com.majestic.nails.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.majestic.nails.dto.PasswordChange;
import com.majestic.nails.repository.UserRepository;
import com.majestic.nails.service.AccountService;

@Component
public class AccountServiceImpl implements AccountService {

	private final UserRepository userRepo;

	@Autowired
	public AccountServiceImpl(UserRepository userRepo) {
		this.userRepo = userRepo;
	}

	@Override
	public Boolean updatePassword(PasswordChange userInfo) {
		if (userInfo == null) {
			return false;
		}
		if (userInfo.getUsername() == null ||
				userInfo.getOldPassword() == null ||
				userInfo.getNewPassword() == null ||
				userInfo.getConfirmPassword() == null) {
			return false;
		}
		if (!userInfo.getNewPassword().equals(userInfo.getConfirmPassword())) {
			return false;
		}
		User user = userRepo.findByUsername(userInfo.getUsername());
		userRepo.save(user);
		return true;
	}

}

package com.doctor.spa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.doctor.spa.dto.PasswordChange;
import com.doctor.spa.entity.User;
import com.doctor.spa.repository.UserRepo;
import com.doctor.spa.service.AccountService;

@Component
public class AccountServiceImpl implements AccountService {

	private final UserRepo userRepo;

	@Autowired
	public AccountServiceImpl(UserRepo userRepo) {
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
		user.setPassword(userInfo.getNewPassword());
		userRepo.save(user);
		return true;
	}

}

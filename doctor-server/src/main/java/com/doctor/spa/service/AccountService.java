package com.doctor.spa.service;

import com.doctor.spa.dto.PasswordChange;

public interface AccountService {
	
	public Boolean updatePassword(PasswordChange userInfo);
}

package com.majestic.nails.service;

import com.majestic.nails.dto.PasswordChange;

public interface AccountService {
	
	public Boolean updatePassword(PasswordChange userInfo);
}
